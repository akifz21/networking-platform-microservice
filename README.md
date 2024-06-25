# Networking Project - Microservices Architecture

This project features a microservices architecture orchestrated using Docker. Each microservice performs different functions and uses PostgreSQL and MongoDB databases. Additionally, service-to-service communication is handled using Feign Client. The user interface interacts with the microservices through the API Gateway.

## Table of Contents

- [Overview](#overview)
- [Architecture Components](#architecture-components)
- [Setup](#setup)
- [Architecture Diagram](#architecture-diagram)
- [Usage](#usage)

## Overview

This project includes several microservices that perform different functions:
- User Service: Manages user operations.
- Job Service: Manages job listings and applications.
- Company Service: Manages company information.
- Message Service: Manages messaging operations.
- Post Service: Manages posts and shares.
- AI Service: Performs artificial intelligence-related tasks.
- API Gateway: The main entry point through which all requests are routed.
- Config Server: Manages the configurations of other microservices.
- Discovery Server: Enables microservices to discover each other.
- User Interface: The frontend user interface.

## Architecture Components

### Services
- **User Service**: Uses PostgreSQL and communicates with Job Service, Company Service, and Post Service via Feign Client.
- **Job Service**: Uses PostgreSQL and communicates with User Service via Feign Client.
- **Company Service**: Uses PostgreSQL and communicates with User Service via Feign Client.
- **Message Service**: Uses MongoDB.
- **Post Service**: Uses PostgreSQL and communicates with User Service via Feign Client.
- **AI Service**: Uses PostgreSQL.
- **API Gateway**: Routes all requests to the appropriate microservice.
- **Config Server**: Manages the configurations of other microservices.
- **Discovery Server**: Enables microservices to discover each other.
- **User Interface**: The frontend user interface, interacts with microservices through the API Gateway.

### Databases
- **PostgreSQL**: Used for User Service, Job Service, Company Service, Post Service, and AI Service.
- **MongoDB**: Used for Message Service.

## Setup

Follow these steps to set up the project locally.

### Prerequisites

- Docker
- Docker Compose

### Steps

1. Clone this repository:
   ```sh
   git clone <repository-url>

2. Change to the project directory:
   ```sh
   cd microservices-architecture-project

3. Build the Docker images:
   ```sh
   docker-compose build

4. Start the Docker containers:
   ```sh
    docker-compose up

## Architecture Diagram

Below is the architecture diagram of the microservices:

![Microservices Architecture Diagram](/diagram.png)

## Usage
Once the application is running, you can access the user interface through your web browser.
The API Gateway will route all requests to the respective microservices.