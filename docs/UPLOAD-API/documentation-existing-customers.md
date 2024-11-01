---
title:  "DEX UPLOAD API Existing Customer Documentation"
description: "Welcome to the DEX UPLOAD API Existing Customer Documentation"
---

# DEX UPLOAD API Existing Customer Documentation

This guide is for our current DEX Upload API users who were onboarded to the API before 10/1/2024.


## What is DEX Upload API?

The CDC Data Exchange (DEX) Upload API is an open-source service created to support public health Data Senders in their effort to share critical public health data with internal CDC Programs. The open-source model allows users to tailor the tool to fit specific data needs. 

### Key Features

The DEX Upload API service is a highly scalable, reliable means of transiting files of nearly any type and size from public health partners to the CDC, even when sent over unreliable network connections. As a current user, your organization will continue to benefit from the DEX Upload API’s robust list of features.

**Resumable Uploads:** Implementation of the Tus service as a library in a Golang application, facilitating high-volume file uploads over HTTP.

**Authentication:** OAuth provider approval enforcement to ensure security.

**Metadata Verification:** Enforcement of upload submission metadata standards.

**File Delivery:** Routing of uploaded files to configured target destinations.

**File Observability:** Upload lifecycle event tracking and endpoints for health check, information, and application version.

**Retry Delivery:** Tool that delivers files to target destinations that uploaded successfully but were unsuccessful in delivery.

**User Interface:** Facilitates uploads and observability within a user interface.

## How Does DEX Upload API Work? 

DEX Upload API is built on [Tus](https://tus.io/), an open-source protocol that allows data submitters to resume data transfers after a communication failure between the submitter and CDC. This feature is essential for data submitters with large amounts of data to share. The DEX Upload API also:

* Features predictable, resource-oriented URLs
* Accepts form-encoded request bodies
* Returns JSON-encoded responses
* Uses standard HTTP response codes and bearer authentication

To learn more about the product and view code examples, you can visit the following DEX Upload API resources:

### Explore the Entire GitHub Repository 

**[DEX Upload API GitHub repository](https://github.com/CDCgov/data-exchange-upload)**

###  Learn More About the Upload API Server

[DEX Upload API Server README](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md)

### Instructions to Configure Your DEX Upload API Instance

[DEX Upload API Configuration README](https://github.com/CDCgov/data-exchange-upload/tree/main/upload-configs)

### View Example Scripts and Returns

[DEX API example scripts](https://github.com/CDCgov/data-exchange-api-examples)

### Helpful Tus and DEX Upload API Information

[DEX OpenAPI (Swagger) specification](https://cdcgov.github.io/data-exchange-api-examples/)

## DEX Upload API Environments

DEX Upload API offers different environments that authenticated users will use during different parts of their onboarding process:

### [DEX Upload API Staging Environment (STG)](https://apistg.cdc.gov/)

Users start in the DEX Upload API STG Environment. Here, the DEX Upload API Team will work with you to conduct end-to-end testing with production-like volume in real-time. We’ll work out any remaining defects before moving to the final environment.

### [DEX Upload API Production Environment (PRD)](https://api.cdc.gov/)

All deployment activities have been sequenced, reviewed, and confirmed by all DEX team members. As a result, teams in the PRD Environment are approved to begin data transfers to the CDC. The DEX Upload API Team will work with you to determine when you’ll make your transfers.

## DEX Upload API Endpoints

DEX Upload API offers different endpoints that authenticated users will need during different parts of the file upload process (note: to successfully reach each endpoint below, your url should include upload/ before your chosen endpoint.

### /

You will use this endpoint to upload multiple files over HTTP. Using this endpoint exposes a server that implements the Tus Resumable Upload protocol. You must implement a Tus client to connect in order to communicate in a way that Tus understands. The endpoint uses a combination of POST, PATCH, and OPTION requests to upload files. This endpoint authenticates users via bearer tokens in the Authorization header.

### /oauth

You will use this endpoint to authenticate with the DEX Upload API. You must have a SAMS system account in order to use this endpoint, as it uses SAMS as the identity provider.

To get a token, enter your username and password in an HTTP POST request. This request routes to the Upload API Management layer, which contains the client credentials needed for the Password Grant flow. With the combination of your user and client credentials, SAMS issues a short-lived authentication token, as well as a refresh token. You can use this token in future requests to the Upload API by providing it as a Bearer token in the Authorization header of your HTTP requests.

### /health

This endpoint allows you to check the status of the Upload API system, and its dependent systems. You can send an HTTP GET request to this endpoint and receive a 200 response with JSON in the body containing the overall status of the system, as well as statuses of dependent systems. This endpoint authenticates users via bearer tokens in the Authorization header.

### /info/{uploadId}

| | |
|-|-|
| Production | api.cdc.gov/upload/info/{upload-id} |
| Staging | apistg.cdc.gov/upload/info/{upload-id} |
| Test | apitst.cdc.gov/upload/info/{upload-id} |
| {upload-id} | Universally Unique Identifier (UUID) of the upload assigned by the upload server | 
| | |

This endpoint serves as a mechanism for you to verify that your upload has completed successfully and that they are available in the underlying storage. You can send an **HTTP GET** request to this endpoint providing your unique ID of your upload as a path parameter.

* If the upload was successful, you will get a 200 response with JSON in the body containing information about your upload such as size in bytes and upload time.
* If your upload is incomplete or in an unavailable state for any reason, you will receive a 404 response.

This endpoint authenticates users via bearer tokens in the Authorization header.

#### /upload/info/{uploadId} Responses

Users can access the **/upload/info/{uploadId}** endpoint to have objects returned from the API that can provide information about a wide range of actions and issues.

#### Manifest Object

The **Manifest Object** provides metadata about the uploaded file. Metadata returned includes the following:

All metadata fields submitted, including –

* DEX Metadata Fields
* Program-Specific Metadata Fields
* Server-Assigned Metadata Fields
* **Upload_Id:** UUID assigned
* **Dex_Ingest_Datetime:** date/time captured following upload id assignment

*** ***

#### File_Info Object

The **File_Info Object** returns the size of the uploaded file in bytes at the time of the GET request. Metadata returned includes the following:

**Size_Bytes:** value returned will vary based on upload progress

* Increases as uploads progress with each successive get
* When an upload is complete, the value returned is the final size of the file uploaded

**Updated_At:** value returned will vary based on upload progress

* When an upload is in progress, the value returned is the datetime of the get request
* When an upload is complete, the value returned is the datetime of upload completion

*** ***

#### Upload_Status Object

The **Upload_Status Object** provides the current status of the upload at the time of the GET request. Metadata returned includes the following:

**Status**: value returned will vary based on upload progress

* Value is “Complete” when the uploads are completed
* Value is “In Progress” when uploads are in progress
* Initiated when manifest configurations for the uploads have been set but when the upload has not started

**Chunk_Received_At:** returns the most recent successful upload chunk processed date/time; if the upload is complete, this value is the date/time when the last chunk completed.

### Deliveries Object

The Deliveries Object provides information about the target file delivery for the upload. Metadata returned includes the following:

**Status:** value returned will vary based on target delivery success

* Value is “Success” when the file is successfully delivered to program target destinations
* Value is “Failed” if the file is unsuccessfully delivered

**Name:** returns the DEX assigned name of the program delivery target location

* One value assigned per program

**Location:** returns the target location to which the file was delivered

* No value returned if delivery was unsuccessful

**Delivered_At:** returns the datetime of file delivery to the target location

* No value returned if delivery was unsuccessful

**Issues:** value returned will include target delivery failure messaging

* When delivery status is Success, this value will be null

## Accessing the DEX Upload API

Current users are already sending data with the DEX Upload API. As a result, the following information will help users access the API.

### Step One: Use SAMS credentials to request an authorization token

All current users will use the [Secure Access Management System (SAMS) Access](https://auth.cdc.gov/siteminderagent/forms/login.fcc?TYPE=33554433\&REALMOID=06-3afcc4aa-a136-4e86-838b-a0e02ec8d56f\&GUID=\&SMAUTHREASON=0\&METHOD=GET\&SMAGENTNAME=-SM-x96DdzjuEYXXSHbO%2fEEIkN2f8j%2b67CA9jCgg83Ii8QBcthGn7RKJUDKSHbjiLob4\&TARGET=-SM-https%3a%2f%2fauth%2ecdc%2egov%2f) to authenticate into the DEX Upload API environments. The DEX Upload API uses a password grant flow, where users exchange their SAMS credentials for an authentication token and a refresh token. The authentication token is valid for one hour. After expiring, the user will need to use the refresh token to get a new auth token. Authentication is managed through the auth API located at the */oauth* endpoint.

To login, provide your SAMS username and password to request an authorization token. This can be done by sending a POST request to the **/oauth** API endpoint.

**Your request must have the following header:**

Content-Type: application/x-www-form-urlencoded

**Provide your SAMS credentials in the following query parameters:**

* username
* password

**An example request URL would look like this:**

<span style="text-decoration:underline;">https://\<hostname>/oauth?username=\<your-username>\&password=\<your-password</span>>

You can authenticate into the[ DEX Upload API Staging Environment (STG)](https://apistg.cdc.gov/upload) for testing or the[ DEX Upload API Production Environment (PRD)](https://api.cdc.gov/upload) to begin real-time, real-world transfer of your public health data to the CDC.

You can also read more about the DEX Upload API’s OAuth requirements in our[ GitHub repository.](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/internal/readme.md)

### Step Two: Configuring and Confirming File Delivery Locations

As an existing DEX Upload API user, you should have your storage locations configured. In the event that you need to add or confirm locations, please consult the [DEX Upload API guidance on configuring upload routing and delivery targets](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#configuring-upload-routing-and-delivery-targets).

### Step Three: Complete Sender Manifest Information

You can read more about our metadata requirements in the DEX Upload API Metadata Requirements section below.

### Step Four: Upload Your File to the DEX Upload API Server

Users will upload files to the DEX Upload API using their chosen Tus resumable upload client. The DEX Upload API accepts uploads using Java, JavaScript, Python, and directly in the user’s browser. Users can view example scripts for each option that can help them build their upload correctly:

[Java](https://github.com/CDCgov/data-exchange-api-examples/tree/main/tus-clients/java)

[NodeJS (JavaScript)](https://github.com/CDCgov/data-exchange-api-examples/tree/main/tus-clients/nodejs)

[Python](https://github.com/CDCgov/data-exchange-api-examples/tree/main/tus-clients/python)

[Browser](https://github.com/CDCgov/data-exchange-api-examples/tree/main/tus-clients/browser)

### Step Five: Use /info endpoint to check file delivery status

Use the /info endpoint to confirm the status of your file transfer. You’ll see one of two responses:

* If the upload was successful, you will get a 200 response with JSON in the body containing information about your upload such as size in bytes and upload time.
* If your upload is incomplete or in an unavailable state for any reason, you will receive a 404 response.

## DEX Upload API Metadata Requirements

Metadata plays an important role in how the DEX Upload API organizes, manages, and routes public health data. As an existing user, the DEX Upload API Team has already worked with you to ensure that your program can meet the requirements for submitting your metadata to the CDC. Your Sender Manifest is the approved metadata for your organization. The Sender Manifest accompanies file uploads initiated by senders. Values in the Sender Manifest are validated against expected values provided by the program and by senders during onboarding. The following fields are required for all submissions:


| **Required Metadata** | | | |
| Metadata field name | Definition and purpose | Type | How to use this field |
|-------|-------|-------|-------| 
| **data_stream_id** (example: celr) | Identifies the CDC program the file should be contained in | String | This value was determined during onboarding |
| **data_stream_route** (example: hl7v2) | Defines the path that data takes through DEX to arrive at the CDC – this can be the name of the internal folder destination for the data file | String | This value was determined during onboarding |
| **sender_id** (example: APHL) | Identifies the sender, machine, or intermediary that’s sending data | String | This value was determined during onboarding |
| **data_producer_id** (example: FL-SPHL) | Identifies the public health authority that supplies the data | String | This value was determined during onboarding (Some data will be sent directly by senders who will not have a data_producer_id – if this is you, enter “null”) |
| **Jurisdiction** (example: FL) | Defines the city, county, state, tribe, or territory where the data originates | String | This value is decided by you and the DEX Upload API Team during onboarding. (If you don’t track this information, the value is “null”) |
| **received_filename** (example: filename.hl7) | Identifies the name of the file being sent | String | The DEX Team worked with you to create unique markers for your file name during your onboarding process to help organize your data submissions. |
| **Version** (example: 2.0) | Defines the version of the metadata being sent | String | This value was determined during onboarding |
| **supporting_metadata** | Metadata specific to your program that you track | String | If you track information for your organization outside the fields above, you can include it under this field |


### Invalid Metadata Characters

There are characters that should never be used when naming your metadata. Using these characters could result in upload failures. Those characters are:

| **Character** | **Character Description** |
|---------------|---------------------------|
| / | forward slash |

The DEX Upload API Team can work with you to ensure that your metadata meets our requirements, but you should also review your naming conventions to make sure that you are not using these special characters – especially in your supporting metadata.

### Metadata Changes for Legacy Users

DEX Upload API’s beta testers and legacy users used an earlier version of the metadata model provided above. This model, also known as Version One (V1), looks significantly different than the current model.

If you’re currently using the V1 metadata model, you can continue to send your data using the V1 model. The DEX Upload API Team will work with you to transition your metadata to the new model.

To prepare for this transition, please review the chart below, which outlines the changes and how they impact your metadata:

| **V1 field name(s)** | **V2 field name** |
|----------------------|-------------------|
| filename | filename |
| meta_ext_filename | |
| original_filename | |
| meta_destination_id | data_stream_id |
| meta_ext_event | data_stream_route | 

Other metadata fields that you collect can also be added to the “supporting_metadata” field in the current metadata model.

## Errors

### Metadata Errors

An issue with metadata may result in an error response such as the one shown below:

```
TusCommunicationError: pre-create hook failed: exit status 1
Using schema_version = 1.0
meta_ext_source = DESTX is not one of the allowed values: ["DEST"]
meta_ext_entity = ENT2 is not one of the allowed values: ["ENT", "ENT1"]
Missing required metadata 'meta_ext_required', description = 'Required.'
Provided metadata: {"filename":"example.jpeg","meta_destination_id":"DESTX","meta_ext_entity":"ENT2","meta_ext_event":"zz","meta_ext_filename":"example.jpeg","meta_ext_objectkey":"f3da1339-5d0f-4111-84e1-e65a4c12b031", "meta_ext_source":"DESTX", "meta_username":"email@email.com"}
```

In this example, the client has failed to provide some metadata that is required for a program/event, and in addition, they provided a metadata value that was not allowed. Responses will vary depending on the Tus client that you decide to use. When your upload is successful, Tus clients that provide status callbacks will indicate the last upload attempt was successful. Others may finish silently, so be sure you choose the client that meets your needs.

### Submission Errors

You can decide which Tus client you want to use to send your data. When an error occurs, all Tus clients provide feedback. Typically, this feedback arrives as exceptions or error handler callbacks, like a 404 error. The response will indicate the reason for the error.

### Certificate Errors

DEX uses Akamai's Let's Encrypt integration to obtain, deploy, and manage SSL/TLS certificates through the Let's Encrypt Certificate Authority. All Upload API users must add this certificate to their certificate store and ensure that their trust store is set to trust “Let’s Encrypt” root certificates.

## Adding Additional Users to SAMS 

If you have a new addition to your team, you’ll need to get your new team member set up with a SAMS account to be able to transfer and view data. To register a new user for SAMS, complete the following steps:

### 1. Contact the DEX Upload API Team

An existing user from your organization should email the DEX Upload API Team at with the name and email address of your new user.

### 2. New user receives invitation 

The DEX Upload API Team will create a custom invitation for your new user. The invitation will arrive in the email you provided to the DEX Team from <span style="text-decoration:underline;">sams-no-reply@cdc.gov</span>. The email subject will be “U.S. Centers for Disease Control: SAMS Partner Portal – Invitation to Register.”

**Can’t find the invitation?**

If your new user doesn’t see their invitation in their inbox or their invitation expired, contact the DEX Team to request that they resend it. If the invitation is not working, reach out to the SAMS Helpdesk at 877-681-2901 or <span style="text-decoration:underline;">samshelp@cdc.gov.</span>

### 3. Prepare user information

The invitation includes a username and temporary password. They'll need to have these ready when they register for SAMS. They’ll also need:

* Legal Name – no nicknames or shortened names
* Home Address
* Organization Name
* Organization Address
* Phone Number

### 3. Register for SAMS

Click the registration link in the invitation. Enter the username and temporary password; the user will be prompted to change their password. Next, they’ll need to validate their legal name, organization, and contact information. To complete the registration, click the “submit” button. \

### 4. SAMS Portal Access Approval

Once the steps above have been completed, the DEX Upload API Team will be notified to approve the access request. This may take a few minutes or a few days, given how busy the team is. Your new users will receive an email from <span style="text-decoration:underline;">sams-no-reply@cdc.gov</span> with the subject “U.S. Centers for Disease Control: SAMS Partner Portal – SAMS Activity Authorization” to confirm their approval.

## Product Help and Support

If you need additional information or have questions, please contact the DEX Upload API Team.

| | |
|-|-|
| Report an incident: | <span style="text-decoration:underline;">ociouploadapimincidencereporting@cdc.gov</span> |
| Contact the team: | <span style="text-decoration:underline;">ociocoeuploadapimteam@cdc.gov</span> |

## Updates, Version & Change History

Users can see real-time product updates in the public[ DEX Upload API GitHub Repository](https://github.com/CDCgov/data-exchange-upload/blob/main/CHANGELOG.md). Specific information about product releases and new features can be found in the[ DEX Upload Release Notes](https://github.com/CDCgov/data-exchange-upload/tree/main/docs/Release%20Notes). To see a history of product changes, visit the[ DEX Upload API Change Log](https://github.com/CDCgov/data-exchange-upload/blob/main/CHANGELOG.md).
