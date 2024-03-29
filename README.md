# Telemedicine System for Hypertensive Patient Management

[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com) [![forthebadge](https://forthebadge.com/images/badges/uses-css.svg)](https://forthebadge.com)

## Project Description
The project aims to develop a telemedicine system for the management of hypertensive patients. The goal is to improve the quality of care and the effectiveness of pharmacological treatment by providing timely and personalized medical interventions. Digital technology will be used for the storage and analysis of data related to blood pressure, symptoms, antihypertensive therapies, and other relevant information.

## Key Features

- Authentication for specialist doctors and patients
- Storage of daily blood pressure readings
- Recording of symptoms, conditions, and concurrent therapies
- Specification of antihypertensive therapies to be followed by patients
- Display of patient data in a summarized form
- Addition or modification of therapies based on the patient's evolving condition
- Input of patient information, including risk factors and past medical conditions
- Verification of adherence to prescribed therapies
- Management of alerts for patients and doctors in case of missed medication
- Notification to doctors of pressures exceeding predefined thresholds
- Communication between patients and doctors via email

## Repository Structure
The repository is organized as follows:

- `documentazione`: Contains project documentation, such as technical specifications and user guides.
- `src`: Contains the source code of the telemedicine system.
- `src/test`: Contains test files to verify the system's functionalities.
- `src/images`: Contains additional resources, such as images or configuration files.

## Architectural Pattern

The system has been designed by adopting the Model-View-Controller (MVC) pattern, natively implemented by JavaFX libraries used for the project's graphical interface. The choice to use MVC was motivated by the desire to have a well-defined logical division of classes within the project, thus leveraging the fundamental principle of MVC, which is decoupling.

Each member of the MVC has been assigned a different package, resulting in the following structure:

### Model:
- Manages the system's data and provides methods to access the database.

### View:
- Displays the data contained in the Model and handles the interaction between the user (whether it is a doctor or patient) and the system.

### Controller:
- Receives user commands (via the View) and implements them by modifying the state of the other two components (Model and View).

![Immagine 2023-07-10 124756](https://github.com/emmekappaa/Hypertensive_Patients/assets/94229712/33f8151e-4540-410a-8557-59fc4c0c9325)


## Application Screenshots

![Immagine 2023-07-10 115002](https://github.com/emmekappaa/Pazienti_Ipertesi/assets/94229712/34be1b99-1bb7-465d-bca0-062eed62aab3)

![Immagine 2023-07-10 115136](https://github.com/emmekappaa/Pazienti_Ipertesi/assets/94229712/c35a7136-2bd8-4083-86b1-6de7ae9ac5c8)

![Immagine 2023-07-10 115057](https://github.com/emmekappaa/Pazienti_Ipertesi/assets/94229712/e20eb1f0-86ca-41e7-9476-ebc7029cf9d1)
