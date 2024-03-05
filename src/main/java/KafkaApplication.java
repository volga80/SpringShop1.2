//import com.ru.volga.SpringShop11.kafka.KafkaProducer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class KafkaApplication implements CommandLineRunner {
//
//    private final KafkaProducer kafkaProducer;
//
//    @Autowired
//    public KafkaApplication(KafkaProducer kafkaProducer) {
//        this.kafkaProducer = kafkaProducer;
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(KafkaApplication.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        kafkaProducer.sendMessage("Hello, Kafka!");
//    }
//}
