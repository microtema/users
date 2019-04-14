package de.microtema.user.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import org.springframework.validation.Validator;

@Configuration
public class RestConfig extends RepositoryRestConfigurerAdapter {

  private final Validator validator;

  public RestConfig(Validator defaultValidator) {
    this.validator = defaultValidator;
  }

  @Override
  public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
    validatingListener.addValidator("beforeCreate", validator );
    validatingListener.addValidator("beforeSave", validator);
  }
}
