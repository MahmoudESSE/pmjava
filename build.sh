#!/usr/bin/env sh

spring init \
	--java-version 22 \
	--build maven \
	--format project \
	--extract . \
	--language java \
	--name pmjava \
	--artifact-id japp \
	--group-id github.mahmoudesse \
	--package-name github.mahmoudesse.pmjava \
	--description "project managemant webapp" \
	--dependencies=web,thymeleaf,devtools,mysql,lombok,validation,security,configuration-processor,jdbc,data-jdbc,data-jpa,data-rest,mail
