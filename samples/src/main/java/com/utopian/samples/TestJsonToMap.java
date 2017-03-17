package com.utopian.samples;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author Ganesh Ramasubramanian
 */
public class TestJsonToMap {
    public static void main(String[] args) {
        String jsonCopperString = "  [\n" +
                "      {\n" +
                "          \"source\": \"network.element.name\",\n" +
                "          \"query_param_name\": \"dslam_name\",\n" +
                "          \"mandatory\": true,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"network.element.ip.address\",\n" +
                "          \"query_param_name\": \"ip_address\",\n" +
                "          \"mandatory\": false,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"network.element.vendor\",\n" +
                "          \"query_param_name\": \"ne_vendor\",\n" +
                "          \"mandatory\": false,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"network.element.family\",\n" +
                "          \"query_param_name\": \"ne_family\",\n" +
                "          \"mandatory\": false,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"network.element.model\",\n" +
                "          \"query_param_name\": \"ne_model\",\n" +
                "          \"mandatory\": false,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"network.element.version\",\n" +
                "          \"query_param_name\": \"ne_version\",\n" +
                "          \"mandatory\": false,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"other.parameters.retrieval.begin.timestamp\",\n" +
                "          \"query_param_name\": \"na_collection_time\",\n" +
                "          \"mandatory\": true,\n" +
                "          \"type\": \"dateFormat\",\n" +
                "          \"format\": \"yyyy-MM-dd'T'HH:mm:ss.SSSXXX\"\n" +
                "      }\n" +
                "  ]\n";

        String jsonFiberString = "  [\n" +
                "      {\n" +
                "          \"source\": \"language.setting\",\n" +
                "          \"query_param_name\": \"language_setting\",\n" +
                "          \"mandatory\": true,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"network.element.vendor\",\n" +
                "          \"query_param_name\": \"network_element_vendor\",\n" +
                "          \"mandatory\": true,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"network.element.ip.address\",\n" +
                "          \"query_param_name\": \"network_element_ip_address\",\n" +
                "          \"mandatory\": true,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"network.element.version\",\n" +
                "          \"query_param_name\": \"network_element_version\",\n" +
                "          \"mandatory\": true,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"network.element.model\",\n" +
                "          \"query_param_name\": \"network_element_model\",\n" +
                "          \"mandatory\": true,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"previous.interval.end.timestamp\",\n" +
                "          \"query_param_name\": \"previous_interval_end_timestamp\",\n" +
                "          \"mandatory\": true,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"data.feed\",\n" +
                "          \"query_param_name\": \"data_feed\",\n" +
                "          \"mandatory\": true,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"product.version\",\n" +
                "          \"query_param_name\": \"product_version\",\n" +
                "          \"mandatory\": true,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"network.element.family\",\n" +
                "          \"query_param_name\": \"network_element_family\",\n" +
                "          \"mandatory\": true,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"network.element.name\",\n" +
                "          \"query_param_name\": \"network_element_name\",\n" +
                "          \"mandatory\": true,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"product.name\",\n" +
                "          \"query_param_name\": \"product_name\",\n" +
                "          \"mandatory\": true,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"previous.interval.begin.timestamp\",\n" +
                "          \"query_param_name\": \"previous_interval_begin_timestamp\",\n" +
                "          \"mandatory\": true,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"file.retrieval.end.timestamp\",\n" +
                "          \"query_param_name\": \"file_retrieval_end_timestamp\",\n" +
                "          \"mandatory\": true,\n" +
                "          \"type\": \"string\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"source\": \"file.retrieval.begin.timestamp\",\n" +
                "          \"query_param_name\": \"file_retrieval_begin_timestamp\",\n" +
                "          \"mandatory\": true,\n" +
                "          \"type\": \"string\"\n" +
                "      }\n" +
                "  ]\n";

        // json data
        ArrayList<AnaMetaData> copperMetaDatas =
                new Gson().fromJson(jsonCopperString, new TypeToken<Collection<AnaMetaData>>() {
                }.getType());
        System.out.println("Parsed Copper MetaData...");
        System.out.println(copperMetaDatas.get(1).isMandatory());
        copperMetaDatas.stream().forEach(System.out::println);

        // fiber data
        ArrayList<AnaMetaData> fiberMetaDatas =
                new Gson().fromJson(jsonFiberString, new TypeToken<Collection<AnaMetaData>>() {
                }.getType());

        System.out.println("Parsed Fiber MetaData...");
        fiberMetaDatas.stream().forEach(System.out::println);
    }

    private static class AnaMetaData {
        private String source;
        @SerializedName("query_param_name")
        private String queryParamName;
        private boolean mandatory;
        private String type;
        private String format;
        private Predicate<String> valueValidator;

        public String getSource() {
            return source;
        }

        public String getQueryParamName() {
            return queryParamName;
        }

        public boolean isMandatory() {
            return mandatory;
        }

        public String getType() {
            return type;
        }

        public String getFormat() {
            return format;
        }

        @Override
        public String toString() {
            return "AnaMetaData{" +
                    "source='" + source + '\'' +
                    ", queryParamName='" + queryParamName + '\'' +
                    ", mandatory=" + mandatory +
                    ", type='" + type + '\'' +
                    ", format='" + format + '\'' +
                    '}';
        }
    }
}
