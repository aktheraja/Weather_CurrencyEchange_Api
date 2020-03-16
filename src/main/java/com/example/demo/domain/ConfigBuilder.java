package com.example.demo.domain;


    import com.posadskiy.currencyconverter.Messages;
    import com.posadskiy.currencyconverter.config.Config;
    import com.posadskiy.currencyconverter.exception.CurrencyConverterException;
    import org.springframework.context.annotation.ComponentScan;
    import org.springframework.stereotype.Component;

@Component
@ComponentScan({"com.example.demo"})
    public class ConfigBuilder {
        private Config config;

        public ConfigBuilder() {
            config = new Config();
        }

        public ConfigBuilder currencyConverterApiApiKey(String apiKey) {
            config.setCurrencyConverterApiApiKey(apiKey);

            return this;
        }

        public ConfigBuilder currencyLayerApiKey(String apiKey) {
            config.setCurrencyLayerApiKey(apiKey);

            return this;
        }

        public ConfigBuilder openExchangeRatesApiKey(String apiKey) {
            config.setOpenExchangeRatesApiKey(apiKey);

            return this;
        }

        public Config build() {
            if (config.getCurrencyConverterApiApiKey() == null && config.getCurrencyLayerApiKey() == null && config.getOpenExchangeRatesApiKey() == null) {
                throw new CurrencyConverterException(Messages.FILL_API_KEY);
            }

            return config;
        }
    }

