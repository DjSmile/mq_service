# Changing status service

General-purpose service for working with order messages getting from different sources (REST, AMQP). 
Service can observe the status of orders and maintaining changes in their status.
Service supporting Rest API и MQ requests.
 
## Use cases
Service can execute orders getting from REST and from AMQP using JSON oriented protocol (unique ordrerId in JSON message). 
`POST /api/request`

Service supply GET REST request for get order information by unique orderId.
`GET /response/{orderId}/`

## Specification

Example JSON request for changing order status:

`{"order_id":72663801,"cancel_allowed":false,"send_message_allowed":false,"comment":"comment","author":"I.B.Ivanov","tech_code":3, "requestType":"1"}`

`cancel_allowed` - Possibility to decline order from the applicant. Default: cancelAllowed=false.
`send_message_allowed` -  Possibility to send text messages from the applicant to the executer of order. Default:sendMessageAllowed=false.

`tech_code` - (or `orgCode`) Status code.  Have to fill only one field of this block
`requestType` - request type.

Example JSON response with result information of changing status operation:

`{ "order_id": 72663801, "message": "operation completed", "code": 0, "complete_on": 1508240327676 }`

### Possibility values of techCode:
1  Order received  from applicant and registered.
2  Order transmitted, but not registered yet.
3  Order executed. 
4  Rejected.
6  Order received only.
7  Intermediate order status. 
9  Applicant canceled order. 
10  Order declined from the applicant.
11  Order declined from the executer.
12  Input message from the executer.
14  Waiting information from the applicant. 
15  Order have to correction from the applicant.


