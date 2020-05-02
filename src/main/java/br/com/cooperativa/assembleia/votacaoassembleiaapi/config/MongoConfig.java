package br.com.cooperativa.assembleia.votacaoassembleiaapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("br.com.cooperativa.assembleia.votacaoassembleiaapi")
public class MongoConfig {
}
