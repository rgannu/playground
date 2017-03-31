/**
 * @author Ganesh Ramasubramanian
 */
public class KafkaJunitTest {
    /*
    @RunWith(Enclosed.class)
    public class KafkaJunitRuleTest {
        private static final String TOPIC = "topicX";

        public static class BaseTest {
            @Rule
            public KafkaJunitRule kafkaRule = new KafkaJunitRule(EphemeralKafkaBroker.create());

            @Test
            public void testKafkaServerIsUp() {
                try (KafkaProducer<String, String> producer = kafkaRule.helper().createStringProducer()) {
                    producer.send(new ProducerRecord<>(TOPIC, "keyA", "valueA"));
                }

                try (KafkaConsumer<String, String> consumer = kafkaRule.helper().createStringConsumer()) {
                    consumer.subscribe(Lists.newArrayList(TOPIC));
                    ConsumerRecords<String, String> records = consumer.poll(500);
                    assertThat(records, is(notNullValue()));
                    assertThat(records.isEmpty(), is(false));

                    ConsumerRecord<String, String> msg = records.iterator().next();
                    assertThat(msg, is(notNullValue()));
                    assertThat(msg.key(), is(equalTo("keyA")));
                    assertThat(msg.value(), is(equalTo("valueA")));
                }
            }
        }*/
}
