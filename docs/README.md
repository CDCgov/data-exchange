---
title:  "DEX (Data Exchange) Product Documentation"
description: "Welcome to the DEX Products Documentation Site!"
permalink: "/"
---


# Introduction

## What Is DEX? 
Public health data systems are critical sources of information that allow our nation to identify and face our most significant health threats. The Centers for Disease Control and Prevention (CDC)’s[ Data Modernization Initiative](https://www.cdc.gov/surveillance/data-modernization/index.html) (DMI) was created to provide better, faster, actionable data sharing for decision-making at all levels of public health.   

The CDC’s Data Exchange (DEX) is essential to the CDC’s DMI efforts. DEX supports public health Data Senders in their effort to share public health data with internal CDC Programs. Data Senders are CDC partners across the country, including:  

- State, tribal, local, and territorial public health authorities  
- Hospitals and health systems  
- Laboratories  
- Trusted Intermediaries   

DEX provides a modern and secure platform for Data Senders to transfer files directly to CDC Programs. DEX supports many standard file formats (HL7v2®, FHIR®, CDA, XML, CSV) and offers the following benefits for users:  

**Data observability –** DEX provides real-time status and tracking information during all stages of their file transfer. 

**Customizable code  –** DEX APIs offer open-source code that your organization can use to create your own solution. 

**Data security –** DEX’s keeps data only in the hands of public health partners who need it. 

**Data-informed decision making –** data submitted to DEX can help predict public health events and inform effective health response. 

## Who Uses DEX? 
DEX facilitates data modernization, integrity, and security between CDC and our public health partners in the field. We all have essential roles in the data transfer process. 

- **Data Senders** are public health partners who send data to the CDC. 
- **Data Receivers** are CDC Programs or intermediaries who retrieve or process data sent by Data Senders.  
- **Data Producers** are public health partners who create public health data. Sometimes, a Data Producer may not be a Data Sender — they may contract with intermediaries to send data. 


Whether you need to send data to the CDC or create a custom data transfer solution that meets your needs, several application programming interfaces (APIs) are available to help you. 

# DEX Products

## DEX Processing Status API 

The DEX Processing Status API (PS API) was developed to provide visibility into the status and performance of their file uploads. The PS API provides the ability to both receive reports about the status of uploads, and to develop custom queries to ask detailed questions about the data being uploaded and processed.  

To learn more about the DEX PS API and find out how you can use it to fit your development needs, check out the following resources:  

- [DEX PS API Quick Start Guide](PS-API/quick-start-guide)
- [DEX PS API Complete Product Guide](PS-API/product-guide)  
- [DEX PS API Public GitHub Repository](https://github.com/CDCgov/data-exchange-processing-status)  

 
## DEX Upload API 

The DEX Upload API provides a modern and secure platform for Data Senders to upload their data directly to CDC Programs and offers the following benefits for users:    

- Resumable and reliable uploads: DEX Upload API is a reliable tool for users to send large files in any format (HL7, FHIR, CDA, XML, CSV). In case of a connection failure, DEX Upload API allows resumable uploads so that users don’t lose their progress during interrupted file transfers.    

- Scalable operations: The API efficiently manages large amounts of data and use cases, making the future state of data sharing more predictable for users.     

To learn more about the DEX Upload API and how it can be customized to fit your needs, check out the following resources:  

- [DEX Upload API Documentation for Existing Customers](UPLOAD-API/documentation-existing-customers)
- [DEX Upload API Quick Start Guide](UPLOAD-API/quick-start-guide)
- [DEX Upload API Complete Product Guide](UPLOAD-API/product-guide)
- [DEX Upload API Public GitHub Repository](https://github.com/CDCgov/data-exchange-upload) 







