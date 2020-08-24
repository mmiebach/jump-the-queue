package com.devonfw.application.jtqj.visitormanagement.logic.impl.usecase;

import java.util.Objects;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.application.jtqj.visitormanagement.dataaccess.api.VisitorEntity;
import com.devonfw.application.jtqj.visitormanagement.logic.api.to.VisitorEto;
import com.devonfw.application.jtqj.visitormanagement.logic.api.usecase.UcManageVisitor;
import com.devonfw.application.jtqj.visitormanagement.logic.base.usecase.AbstractVisitorUc;

/**
 * Use case implementation for modifying and deleting Visitors
 */
@Named
@Validated
@Transactional
public class UcManageVisitorImpl extends AbstractVisitorUc implements UcManageVisitor {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(UcManageVisitorImpl.class);

  @Override
  public boolean deleteVisitor(long visitorId) {

    VisitorEntity visitor = getVisitorRepository().find(visitorId);
    getVisitorRepository().delete(visitor);
    LOG.debug("The visitor with id '{}' has been deleted.", visitorId);
    return true;
  }

  @Override
  public VisitorEto saveVisitor(VisitorEto visitor) {

    String password = visitor.getPassword();
    boolean hasLowerCase = false;
    boolean hasUpperCase = false;
    boolean hasNumber = false;
    Objects.requireNonNull(visitor, "visitor");

    if (password.length() < 6) {
      throw new IllegalArgumentException("password too short");
    }

    for (int i = 0; i < password.length(); i++) {
      char character = password.charAt(i);
      if (Character.isLowerCase(character)) {
        hasLowerCase = true;
      }
      if (Character.isUpperCase(character)) {
        hasUpperCase = true;
      }
      if (Character.isDigit(character)) {
        hasNumber = true;
      }
    }
    if (!hasNumber) {
      throw new IllegalArgumentException("has no Number!");
    }
    if (!hasLowerCase) {
      throw new IllegalArgumentException("has no lower case!");
    }
    if (!hasUpperCase) {
      throw new IllegalArgumentException("has no upper case!");
    }

    VisitorEntity visitorEntity = getBeanMapper().map(visitor, VisitorEntity.class);

    // initialize, validate visitorEntity here if necessary
    VisitorEntity resultEntity = getVisitorRepository().save(visitorEntity);
    LOG.debug("Visitor with id '{}' has been created.", resultEntity.getId());
    return getBeanMapper().map(resultEntity, VisitorEto.class);
  }
}
