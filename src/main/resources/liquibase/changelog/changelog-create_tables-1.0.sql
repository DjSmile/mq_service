--liquibase formatted sql

--changeset sstotskiy:request
CREATE TABLE mq_service.request
(
  id      BIGSERIAL      NOT NULL
    CONSTRAINT request_pkey
    PRIMARY KEY,
  order_id BIGINT NOT NULL,
  cancel_allowed  BOOLEAN,
  send_message_allowed  BOOLEAN,
  author VARCHAR(60),  
  comment   VARCHAR(128),
  org_code  VARCHAR(32),
  tech_code INTEGER,
  registered_on TIMESTAMP,
  uid VARCHAR(200)
);

--changeset sstotskiy:request_INDEX
CREATE UNIQUE INDEX mq_service_request_oder_id
  ON mq_service.request (order_id);

--changeset sstotskiy:response
CREATE TABLE mq_service.response
(
  id      BIGSERIAL   NOT NULL
    CONSTRAINT response_pkey
    PRIMARY KEY,
  order_id BIGINT NOT NULL,
    CONSTRAINT response_order_id_fk FOREIGN KEY (order_id)
    REFERENCES mq_service.request(order_id),
  message   VARCHAR(128) NOT NULL, 
  code INTEGER  NOT NULL,
  complete_on   TIMESTAMP default now(),
  uid VARCHAR(200)
);