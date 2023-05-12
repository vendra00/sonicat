# Project Sonicat Test

[![Build Status](https://travis-ci.org/sonicat/test.svg?branch=master)](https://travis-ci.org/sonicat/test)
[![Coverage Status](https://coveralls.io/repos/github/sonicat/test/badge.svg?branch=master)](https://coveralls.io/github/sonicat/test?branch=master)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/MIT)

[Test project for Sonicat Systems.]

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## Introduction

Sonicat is a web application designed to facilitate testing and analysis of yeasts and crystals. It provides a RESTful API for creating and managing tests, storing test data, and retrieving test results.


## Features

- Create and manage tests for yeasts and crystals.
- Store and retrieve test data in a MongoDB database.
- Calculate and display test results.
- Supports pagination for retrieving a list of tests.
- Comprehensive API documentation.

## Installation

To install and run Sonicat locally, follow these steps:

1. Clone the repository: `git clone https://github.com/vendra00/sonicat.git`
2. Navigate to the project directory: `cd sonicat`
3. Install the dependencies: `mvn clean install`
4. Start the application: `mvn spring-boot:run`

Make sure you have Java 17 and Maven installed on your machine before proceeding.

## Usage

Once the application is running, you can interact with it using the provided RESTful API endpoints. Here are some sample API endpoints:

- `POST /sonicat/yeasts`: Create a new yeasts test.
- `POST /sonicat/crystals`: Create a new crystals test.
- `GET /sonicat/tests?page=1&size=10`: Retrieve a paginated list of tests.

Refer to the [API Documentation](#api-documentation) section for more details on the available endpoints and request/response examples.

## API Documentation

The Sonicat API is documented using Swagger UI. You can access the API documentation by following these steps:

1. Start the application: `mvn spring-boot:run`
2. Open your web browser and navigate to: `http://localhost:8080/swagger-ui.html`

The Swagger UI page will display the available endpoints, request/response models, and allow you to interact with the API.

## Contributing

Contributions to Sonicat are welcome! If you find any issues or would like to suggest enhancements, please submit a bug report or a feature request in the issue tracker.

To contribute code, follow these steps:

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/your-feature-name`
3. Make your changes and commit them: `git commit -am 'Add some feature'`
4. Push the changes to your forked repository: `git push origin feature/your-feature-name`
5. Open a pull request in the main repository.

Please adhere to the existing code style and conventions used in the project.

## License

This project is licensed under the [MIT License](LICENSE).