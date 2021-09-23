# Atm console app
Simple console application that simulates an ATM functions.
## Application features
When the application is launched, data about the ATM is loaded (from txt-file) - the balance of the ATM, as well as a predefined set of bank cards:
- �ard 1111-1111-1111-1111 PIN 1111;
- �ard 2222-2222-2222-2222 PIN 2222;
- �ard 3333-3333-3333-3333 PIN 3333;

The user enters the card number (in the format XXXX-XXXX-XXXX-XXXX), pin and gains access to the following functions
- balance check;
- �ash withdrawal;
- balance replenishment;

After the completion of the work, information about the ATM (ATM balance, bank card balance) will be saved to a file

Additionally*

- menu for application (console input);
- pin code can be entered incorrectly 3 times, after which the card must be blocked;
- card blocking is removed automatically after 24 hours.





