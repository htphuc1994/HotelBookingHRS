# Read Me
## Build locally: 
```
./gradlew clean build
```

## Swagger 
```
http://localhost:8080/webjars/swagger-ui/index.html
```


# Architecture Overview
## Core Components
* AWS Lambda: Handle business logic, such as processing booking requests and interacting with external APIs.
* Amazon API Gateway: Serve as the entry point for all client requests to the system.
* Amazon EventBridge: Manage event routing between services, enabling a decoupled architecture.
* Amazon S3 and DynamoDB: Store persistent data like booking records and user information.
* Amazon RDS / Aurora: Handle transactional data that requires relational database management.
* AWS Step Functions: Orchestrate workflows for complex booking processes.
* Amazon SQS and SNS: Manage message queues and notifications for service communication.
* AWS Secrets Manager: Securely store and manage API keys and credentials for external service integration.
## Diagram Representation
The architecture can be visually represented in a diagram showing the interconnections between these services. For simplicity in this explanation, we'll describe the flow:

### User Interaction:

* Users interact with the system via a frontend application (not depicted here but could be hosted using Amazon S3 and CloudFront).
* The frontend communicates with backend services through the API Gateway.
### Booking Process:

* A booking request is received at API Gateway and routed to a Lambda function.
* Lambda processes the request and publishes an event to EventBridge.
* EventBridge routes the event to other Lambda functions or Step Functions based on rules (e.g., payment processing, external API communications for availability checks).
### Data Handling:

* Booking details are stored in DynamoDB or RDS, depending on the data requirements.
* Logs and transaction records can be stored in S3 and monitored using AWS CloudTrail and CloudWatch.
### External API Integration:

* External APIs for payments and hotel data are integrated using Lambda functions, which securely store API keys in AWS Secrets Manager.
* Lambda functions handle API requests and responses, managing errors and retries.
### Notification and Response:

* Upon successful booking, confirmation is sent to the user via email or SMS using SNS.
* SQS could be used to queue messages for processing tasks that do not require immediate response.

### Deployment: Infrastructure as Code
* Using AWS CloudFormation (or alternatively, AWS CDK or Terraform), you can script the entire deployment. This approach ensures reproducibility, maintainability, and alignment with DevOps practices.