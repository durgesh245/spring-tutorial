package com.example.awslambdademo;

import org.springframework.cloud.function.adapter.aws.SpringBootKinesisEventHandler;

public class OrderHandelar extends SpringBootKinesisEventHandler<String, Object> {
}
