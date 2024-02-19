# AWS Global Dashboard

## Overview
AWS Global Dashboard is a RESTful web application built on Java, Spring boot, AWSCLient, AWS SDK that provides a convenient way to view a list of services instances across all regions.

## Features
- Retrieve a list of Services instances from all AWS regions
- Display instance details including ID, instance type, state, launch time, and region
- Simple and intuitive RESTful API interface
- Scalable and efficient design leveraging AWS services

## Getting Started
To run the application locally setup AWS Cli and configure AWS Cli on local, follow these steps:

### Prerequisites
- AWS account with appropriate permissions to access AWS Services.
- AWS SDK configured with access keys or IAM role.
- IAM User access key and secret key create new key if required.
![image](https://github.com/rishabh-44/Aws-Global-Dashboard/assets/55579816/9efc5fad-62f2-487f-9d75-1256a6355a17)

### Installation
1. Clone this repository to your local machine.
2. Open project in an IDE(IntelliJ preferred).
3. Select JDK version to JDK 11 in project structure same apply for all module.
4. Download all the dependencies required.
5. Download and install AWS CLI by following this article
   [Install AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)
6. use `aws --version` command to see whether aws cli is downloaded or not.
![image](https://github.com/rishabh-44/Aws-Global-Dashboard/assets/55579816/671f7299-f47e-44c5-b984-835bc80bcd27)

   
### Configuration
1. Configure your AWS IAm user on AWS cli by following this article [AWS Configure](https://docs.aws.amazon.com/cli/latest/userguide/cli-authentication-user.html#cli-authentication-user-configure-wizard).
2. Choose region name and output format as mentioned below:
![image](https://github.com/rishabh-44/Aws-Global-Dashboard/assets/55579816/2de7466b-7198-4479-832a-3105689c277a)
3. Ensure your IAM role or user has the necessary permissions to access services.
4. Use `aws iam get-user` command to check if AWS Cli configured successfully.
![image](https://github.com/rishabh-44/Aws-Global-Dashboard/assets/55579816/38eb3b9e-4d88-4e9e-9d81-fb8e21d19ff3)

### Usage
1. Build and run the application.
2. Hit the below Get API in web browser or PostMan `http://localhost:8080/EC2/instances`

3. Access the API endpoints to retrieve EC2 instance information:
- `/instances` - Retrieve a list of all EC2 instances available in different region.
![image](https://github.com/rishabh-44/Aws-Global-Dashboard/assets/55579816/7ee19366-9649-46bf-97bf-8db44340127a)

#### Note : 
- If you get internal server error or any other error then check your JDK version in your project setting. Change your SDK version to java 11 everywhere.
- Go to projects module’s project setting and change the SDK version of all modules to java 11

## Contributing
Contributions are welcome! If you have suggestions, bug reports, or feature requests, please open an issue or submit a pull request.

## Contact
For questions or support, feel free to contact the project maintainer:

[Rishabh Raghuwanshi](mailto:rishabh.raghuwanshi.2612@gmail.com)
