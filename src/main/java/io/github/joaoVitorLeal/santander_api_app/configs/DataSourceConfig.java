package io.github.joaoVitorLeal.santander_api_app.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driverClassName:org.postgresql.Driver}")
    String driver;

    @Bean
    public DataSource hikariDataSource() {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(this.url);
        hikariConfig.setUsername(this.username);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driver);

        /// Configurando as propriedades do pool de conexões do Data Source
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(1);
        hikariConfig.setPoolName("santander-app-db-pool");
        hikariConfig.setMaxLifetime(600000); // 10 minutos
        hikariConfig.setMinimumIdle(300000); // 5 minutos
        hikariConfig.setConnectionTimeout(100000L);
        hikariConfig.setConnectionTestQuery("select 1");

        return new HikariDataSource(hikariConfig);
    }
}
