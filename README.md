# Atm console app
Simple console application that simulates an ATM functions.
## Application features
When the application is launched, data about the ATM is loaded (from atm.txt) - the balance of the ATM, as well as a predefined set of bank cards:
- card 1111-1111-1111-1111 PIN 1111;
- card 2222-2222-2222-2222 PIN 2222;
- card 3333-3333-3333-3333 PIN 3333;

The user enters the card number (in the format XXXX-XXXX-XXXX-XXXX), pin and gains access to the following functions
- balance check;
- cash withdrawal;
- balance replenishment;

After the completion of the work, information about the ATM (ATM balance, bank card balance) will be saved to atm.txt.

Additionally*

- menu for application (console input);
- start.bat file to start the application
- pin code can be entered incorrectly 3 times, after which the card must be blocked;
- card blocking is removed automatically after 24 hours.

## Tech

- Project SDK: 11 java version;
- Project language level: 11 ;






