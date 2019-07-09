curl http://localhost:8080/mq_service/request/ -H "Content-Type: application/json" -X POST -d {\"order_id\":7,\"cancel_allowed\":false,\"send_message_allowed\":false,\"comment\":\"comment\",\"author\":\"I.B.Ivanov\",\"tech_code\":23,\"org_code\":\"org_code\"}
curl http://localhost:8080/mq_service/response/7/ -X GET
curl http://localhost:8080/mq_service/response/7/ -X POST
curl http://localhost:8080/mq_service/response/7/ -X GET

