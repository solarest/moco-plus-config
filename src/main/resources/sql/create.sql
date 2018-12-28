create table moco_request_config
(
  id          INTEGER
    primary key,
  description text,
  uri         text,
  method      text,
  headers     text,
  xpath       text,
  queries     text,
  response    text,
  hash        text
);
create unique index moco_request_config_hash_uindex
  on moco_request_config (hash);

