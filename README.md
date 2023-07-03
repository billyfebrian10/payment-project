# payment-test

payment-app is the frontend application.
- to run this app, go to payment-app dir and run npm start
- to access the web open http://localhost:3000/

payment-project-assignment is the backend application.
- to run this app, go to payment-project-assignment/src/main/java/com/billyfebrian/payment/project/assignment/PaymentProjectAssignmentApplication.java and run the main application.
- database postgresql database name : payment
- to access the api open http://localhost:8080/

API payments
 <h2>Create Payment:</h2> <br/>

  POST http://localhost:8080/payments/ <br/>
  Request : </br>
```json
{
  "amount" : 4650400,
  "paymentTypeId" : 2,
  "customerId" : 1
}
```
Response : <br/>
``` json
{
  "status": 200,
  "errorCode": "OK",
  "errorMessage": "OK",
  "value": {
      "paymentId": 19,
      "amount": 4650400.0,
      "paymentType": "CREDIT",
      "customerId": 1,
      "paymentDate": 1688396929910
  }
}
```
  
<h2>Create Payment:</h2> <br/>

GET http://localhost:8080/payments/{paymentId} <br/>
Request : - </br>
Response : <br/>
``` json
{
    "status": 200,
    "errorCode": "OK",
    "errorMessage": "OK",
    "value": {
        "paymentId": 6,
        "amount": 55000.0,
        "paymentType": "CASH",
        "customerId": 1,
        "paymentDate": 1688044844145
    }
}
```
<h2>Update Payment</h2> <br/>

  PUT http://localhost:8080/payments/{paymentId} <br/>
  Request : </br>
```json
{
  "amount" : 4650400,
  "paymentTypeId" : 2,
  "customerId" : 1
}
```
Response : <br/>
``` json
{
  "status": 200,
  "errorCode": "OK",
  "errorMessage": "OK",
  "value": {
      "paymentId": 19,
      "amount": 4650400.0,
      "paymentType": "CREDIT",
      "customerId": 1,
      "paymentDate": 1688396929910
  }
}
```

<h2>Delete Payment</h2> <br/>

DELETE http://localhost:8080/payments/{paymentId} <br/>
Request : - </br>
Response : <br/>
``` json
{
    "status": 200,
    "errorCode": "OK",
    "errorMessage": "OK"
}
```

<h2>Filter Payment</h2>

GET http://localhost:8080/payments?customerId=1&paymentType=CREDIT&limit=10&page=1 <br/>

Parameter : <br/>
<table>
  <tr>
    <th>Parameter</th>
    <th>Type</th>
    <th>Mandatory</th>
  </tr>
  <tr>
    <td>customerId</td>
    <td>Long</td>
    <th>Yes</th>
  </tr>
  <tr>
    <td>paymentType</td>
    <td>String (CASH/CREDIT)</td>
    <th>Yes</th>
  </tr>
   <tr>
    <td>limit</td>
    <td>Integer</td>
    <th>Yes</th>
  </tr>
    <tr>
    <td>page</td>
    <td>Integer (start from 1)</td>
    <th>Yes</th>
  </tr>
  <tr>
    <td>dateFrom</td>
    <td>Long</td>
    <th>No</th>
  </tr>
  <tr>
    <td>dateTo</td>
    <td>Long</td>
    <th>No</th>
  </tr>
</table>

Response: <br/>
```json
{
    "status": 200,
    "errorCode": "OK",
    "errorMessage": "OK",
    "value": {
        "totalRecords": 13,
        "totalPage": 13,
        "pageSize": 1,
        "data": [
            {
                "paymentId": 19,
                "amount": 4650400.0,
                "paymentType": "CREDIT",
                "customerId": 1,
                "paymentDate": 1688396929910
            }
        ]
    }
}
```
