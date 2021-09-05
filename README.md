## 码匠社区

## 资料
[spring 文档](https://spring.io/guides)

[要仿照的网站](https://elasticsearch.cn/)

[boostrap文档](https://v3.bootcss.com/components)

## 工具
git

## sql
```sql
create table USER
(
  ID         int auto_increment default primary key not null,
  ACCOUNT_ID VARCHAR(100),
  NAME       VARCHAR(50),
  TOKEN      CHAR(36),
  GMT_CREATE BIGINT,
  GMT_MODIFY BIGINT
);
```