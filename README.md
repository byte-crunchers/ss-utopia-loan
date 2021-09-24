# ss-utopia-loan

### Loans microservice
- Backend microservice that handles various types of loans.
- Routes are secured with JWT authorization & HTTPS.
- Routes are accessed through Spring cloud gateway & Eureka server.

#### Database usage:
- User is shown loan choices from the `loan_types` table.
- User selects a loan type & fills out the application form.
- The data from the form is stored in the `loans` table as a new loan instance.

#### REST controller methods:	
- Create a new loan type
- Get all loan types
- Apply for a loan

#### Types of loans:
- Mortgage
- Auto Loan
- Student Loan
- Personal Loan
- Payday Loan
