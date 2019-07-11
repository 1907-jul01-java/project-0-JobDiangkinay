FROM postgres:10
ENV POSTGRES_USER projectzero
ENV POSTGRES_PASSWORD password
ADD schema.sql /docker-entrypoint-initdb.d
EXPOSE 5432