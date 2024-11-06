---
title:  "DEX UPLOAD API Complete Product Guide"
description: "Welcome to the DEX UPLOAD API Complete Product Guide"
---

# Complete Product Guide for the DEX Upload API

## Introduction

### Product Overview

The CDC Data Exchange (DEX) Upload API is an open-source service created to support public health Data Senders in their effort to share critical public health data with internal CDC Programs. The open-source model allows users to tailor the tool to fit specific data needs. \

### Key Features

The DEX Upload API service is a highly scalable, reliable means of transiting files of nearly any type and size from public health partners to the CDC, even when sent over unreliable network connections. As a current user, your organization will continue to benefit from the DEX Upload API’s robust list of features.

**Resumable Uploads:** Implementation of the Tus service as a library in a Golang application, facilitating high volume file uploads over HTTP.

**Authentication:** OAuth provider approval enforcement to ensure security.

**Metadata Verification:** Enforcement of upload submission metadata standards.

**File Delivery:** Routing of uploaded files to configured target destinations.

**File Observability:** Upload lifecycle event tracking and endpoints for health check, information, and application version.

**Retry Delivery:** Tool that delivers files to target destinations that uploaded successfully but were unsuccessful in delivery.

**User Interface:** Facilitates uploads and observability within a user interface.

### Integration Examples

The DEX Upload API accepts uploads using any client that implements the TUS protocol. Users can view example scripts for clients that can help them build their upload client correctly:

 [Java](https://github.com/CDCgov/data-exchange-api-examples/tree/main/tus-clients/java)

[NodeJS (JavaScript)](https://github.com/CDCgov/data-exchange-api-examples/tree/main/tus-clients/nodejs)

[Python](https://github.com/CDCgov/data-exchange-api-examples/tree/main/tus-clients/python)

[Browser](https://github.com/CDCgov/data-exchange-api-examples/tree/main/tus-clients/browser)

## Installation and Setup

### Getting Started

The DEX Upload API GitHub Repository has all the information you need to get set up. Review the[ Getting Started section](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#getting-started) and access information about:

1. [Installation of required tools](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#1-install-required-tools)

    A Tus client and Go are required; you will also need Docker or Podman.

2. [Cloning the repository](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#2-clone-the-repo)

    An easy step to get your instance running.

3. [Starting the server](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#3-start-the-server)

    A step-by-step instructions and commands for building, starting, and running the DEX Upload API.

## Configuration and Usage

### Product Configuration

The DEX Upload API supports configuration with Mac/Linux or Windows. You can review how to configure your instance in the[ Configuration section](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#configurations) of the GitHub Repository and learn about:

1. [General API configuration documentation](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/docs/env-configs.md)

    General configuration information can be found here; the[ env-configs](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/docs/env-configs.md) section of the repository has more detailed information.

2. [Common service configurations](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#common-service-configurations)

    General code to support service configurations.

3. [Configuring Distributed File Locking with Redis](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#configuring-distributed-file-locking-with-redis)

    To scale this service horizontally, you'll need to use a distributed file-locking mechanism to prevent upload corruption. You can read more about the limitations of Tus's support for concurrent requests[ here](https://tus.github.io/tusd/advanced-topics/locks/).

## Metadata

Current DEX Upload API customers use the[ DEX Upload API Metadata Json Schema](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-configs/sender-manifest.2.0.0.schema.json) to determine the data they collect and transfer to the CDC. The model includes:

<table>
  <tr>
    <td>
      <strong>data_stream_id</strong>
      <p style="text-align: center"></p>

(example: celr)
    </td>
    <td>
Identifies the CDC program the file should be contained in
    </td>
  </tr>
  <tr>
    <td>
      <strong>data_stream_route</strong>
      <p style="text-align: center"></p>

(example: hl7v2)
    </td>
    <td>
Defines the path that data takes through DEX to arrive at the CDC – this can be the name of the internal folder destination for the data file
    </td>
  </tr>
  <tr>
    <td>
      <strong>sender_id</strong> 
(example: APHL)
    </td>
    <td>
Identifies the sender, machine, or intermediary that’s sending data
    </td>
  </tr>
  <tr>
    <td>
      <strong>data_producer_id</strong> 
(example: FL-SPHL)
    </td>
    <td>
Identifies the public health authority that supplies the data
    </td>
  </tr>
  <tr>
    <td>
      <strong>Jurisdiction</strong>
      <p style="text-align: center"></p>

(example: FL)
    </td>
    <td>
Defines the city, county, state, tribe, or territory where the data originates
    </td>
  </tr>
  <tr>
    <td>
      <strong>received_filename</strong> 
(example: filename.hl7)
    </td>
    <td>
Identifies the name of the file being sent
    </td>
  </tr>
  <tr>
    <td>
      <strong>Version</strong>
      <p style="text-align: center"></p>

(example: 2.0)
    </td>
    <td>
Defines the version of the metadata being sent
    </td>
  </tr>
  <tr>
    <td>
      <strong>supporting_metadata</strong>
    </td>
    <td>
Metadata specific to your program that you track
    </td>
  </tr>
</table>

Your organization can model your metadata on the DEX Upload API metadata model fields above. As the DEX Upload API evolves, users will be able to create their own custom metadata fields.

## Storage and Routing

### Configuring Storage Backends

Configuring file storage allows users with custom instances to decide where transferred files will be delivered. This is especially important for users with more than one delivery target.[ Read more about how to set up your file storage.](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#configuring-upload-routing-and-delivery-targets)

The DEX Upload API currently supports[ local file system](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#local-file-system),[ Azure](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#azure-storage-account), and[ AWS](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#s3) as storage backends. You can only use one storage backend at a time. If Azure configurations are set, it will be the default storage backend. If the Azure configurations are not set and the AWS S3 configurations are set, S3 will be the storage backend. If neither Azure nor S3 configurations are set, local storage will be the storage backend.

## Routing and Access Control

DEX Upload API routing defines how and where data will arrive once it has been transferred. Set up begins with the creation of a YML file that defines delivery groups, and one or more delivery targets.[ You can read more about configuring routing and delivery targets](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#configuring-upload-routing-and-delivery-targets) to make sure your data lands where you want it to.

## Reports and Events

DEX Upload API publishes **reports** and **events**. While both are published, they offer different benefits and are stored in different locations.

### Reports

DEX Upload API **reports** offer observability so that users can understand what the application is doing with your data. By default reports:

* Are written to the .**/uploads/reports directory** in the local file system.
* Will get appended to files with the naming convention of **\<upload_id>_\<report type>**.

You can set the **LOCAL_REPORTS_FOLDER** environment variable to set the location where these files get written. If you want to publish these reports to a separate service, you can configure a messaging broker like Azure Service Bus or AWS SNS/SQS.

You can read more about how to[ configure your reports location.](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#configuring-the-reports-location)

### Events

DEX Upload API events let the API know that a file has been uploaded and is ready to be delivered to its destination location. By default, event messages about upload and delivery activity are written to the **./uploads/events directory** in the local file system.

You can choose to publish event messages to a message broker like Azure Service Bus or AWS SNS/SQS. Then, you can set environment variables with details related to the new message broker topic for future use in your application or environment. For detailed information about this process, read more about[ configuring the event publication and subscription.](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#configuring-the-event-publication-and-subscription)

## Testing

### Smoke Testing

The DEX Upload API uses smoke test suites that leverage kotlin (/smoke/kotlin) and playwright (/smoke/playwright).

[Kotlin Smoke Test README](https://github.com/CDCgov/data-exchange-upload/blob/main/tests/smoke/kotlin/README.md)

[Playwright Smoke Test README](https://github.com/CDCgov/data-exchange-upload/blob/main/tests/smoke/playwright/README.md)

### Unit Testing

Unit testing helps you catch issues early in development and ensure that individual parts of the API function correctly.[ Read more about how to run basic unit tests.](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#unit-tests)

### Integration Testing

Ensure that all parts of the API are working together and functioning as expected.[ Read more about DEX Upload API integration testing.](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#integration-tests-with-minio-and-azurite)

## Authentication and Authorization

### Overview of Authentication Systems

The Upload API has OAuth token verification middleware for the **/files/** and **/info** endpoints. You can read more detailed information about this topic[ here](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/internal/readme.md).

### Supported Protocols and Standards

Currently, DEX Upload API customers use the[ Secure Access Management System (SAMS) Access](https://auth.cdc.gov/siteminderagent/forms/login.fcc?TYPE=33554433\&REALMOID=06-3afcc4aa-a136-4e86-838b-a0e02ec8d56f\&GUID=\&SMAUTHREASON=0\&METHOD=GET\&SMAGENTNAME=-SM-x96DdzjuEYXXSHbO%2fEEIkN2f8j%2b67CA9jCgg83Ii8QBcthGn7RKJUDKSHbjiLob4\&TARGET=-SM-https%3a%2f%2fauth%2ecdc%2egov%2f) to authenticate into the DEX Upload API environments. If your organization is a CDC public health partner, there is a good chance that you already have a SAMS account associated with your email or work with someone who does.

If you are not a public health partner of the CDC, you will need to wait until custom authorization and authentication methods are incorporated into the product (expected: TBD).

### Custom Authorization and Authentication Methods

As the product evolves, the DEX Upload API will support standard OAuth2 resource provider behaviors, offering flexibility and security based on configuration. Users will be able to configure their own OAuth2 authentication using Okta or another token-based service. While this feature is not available today, check back for product updates.

## Troubleshooting and Debugging

### Troubleshooting

The DEX Upload API has endpoints that can provide information about the status of your file uploads, transfers, and deliveries.

/info

This endpoint serves as a mechanism for you to verify that your upload has completed successfully and that they have been delivered to your chosen underlying storage location. You can send an **HTTP GET** request to this endpoint providing your unique ID of your upload as a path parameter.

* If the upload was successful, you will get a 200 response with JSON in the body containing information about your upload such as size in bytes and upload time.
* If your upload is incomplete or in an unavailable state for any reason, you will receive a 404 response.

/health

You can use the **/health** endpoint to check the status of the Upload API system, and its dependent systems, like your delivery target connection. Send an HTTP GET request to this endpoint and receive a 200 response with JSON in the body containing the overall status of the system, as well as statuses of dependent systems.

### Common Issues and Solutions

When an error occurs, all Tus clients provide feedback. Typically, this feedback arrives as exceptions or error handler callbacks, like a 404 error. The response will indicate the reason for the error. The following errors are the most common errors experienced by users.

#### Manifest Validation Errors

#### Upload Errors

#### Host Forwarding Mismatch Errors

Host forwarding mismatch errors arise when there is a discrepancy between the expected host or forwarding settings in your setup. This error usually indicates that the Tus server or the reverse proxy (like a load balancer) is misconfigured or that there’s an issue with the headers. To troubleshoot this error:

1. Ensure your reverse proxy or load balancer (like AWS ALB) is configured to pass along these headers correctly.
2. Make sure that all traffic between the client, proxy, and Tus server maintains the same protocol. If using HTTPS, ensure everything uses HTTPS. If your Tus client uses http:// but the server expects https://, a mismatch error can occur.
3. Double-check the endpoint URL in your client configuration to ensure it matches the server’s endpoint.

#### Delivery Errors

The following 400 responses indicate an issue with delivery:

***409 Error***

Two parallel PATCH requests could overwrite each other’s data. your Tus client gets a 409 response for parallel uploads, you need to upload the distributed locking. The DEX Upload API currently supports Redis.[ Read more about configuring Distributed File Locking with Redis.](https://github.com/CDCgov/data-exchange-upload/blob/main/upload-server/readme.md#configuring-distributed-file-locking-with-redis)

## Product Support and Resources

If you need additional information or have questions, please contact the DEX Upload API Team.

<table>
<tr>
<td>
Report an incident: <span style="text-decoration:underline;">dexuploadapi@cdc.gov</span>
</td>
</tr>
<tr>
<td>
Contact the team: <span style="text-decoration:underline;">dexuploadapi@cdc.gov</span>
</td>
</tr>
</table>

## Appendices

**[DEX Upload API Version History and Changelog](https://github.com/CDCgov/data-exchange-upload/blob/main/CHANGELOG.md)**
