package com.devonfw.application.jtqj.visitormanagement.dataaccess.api;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.devonfw.application.jtqj.general.common.api.validation.EmailExtended;
import com.devonfw.application.jtqj.general.common.api.validation.Phone;
import com.devonfw.application.jtqj.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.application.jtqj.visitormanagement.common.api.Visitor;

@Entity
@Table(name = "Visitor")
public class VisitorEntity extends ApplicationPersistenceEntity implements Visitor {

  @NotNull
  @EmailExtended
  private String username;

  @NotNull
  private String name;

  @Phone
  @NotNull
  private String phoneNumber;

  @NotNull
  private String password;

  @NotNull
  private Boolean acceptedCommercial;

  @NotNull
  private Boolean acceptedTerms;

  @NotNull
  private Boolean userType;

  private static final long serialVersionUID = 1L;

  /**
   * @return the username
   */
  @Override
  public String getUsername() {

    return this.username;
  }

  /**
   * @param username the username to set
   */
  @Override
  public void setUsername(String username) {

    this.username = username;
  }

  /**
   * @return the name
   */
  @Override
  public String getName() {

    return this.name;
  }

  /**
   * @param name the name to set
   */
  @Override
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return the phoneNumber
   */
  @Override
  public String getPhoneNumber() {

    return this.phoneNumber;
  }

  /**
   * @param phoneNumber the phoneNumber to set
   */
  @Override
  public void setPhoneNumber(String phoneNumber) {

    this.phoneNumber = phoneNumber;
  }

  /**
   * @return the password
   */
  @Override
  public String getPassword() {

    return this.password;
  }

  /**
   * @param password the password to set
   */
  @Override
  public void setPassword(String password) {

    this.password = password;
  }

  /**
   * @return the acceptedCommercial
   */
  @Override
  public Boolean getAcceptedCommercial() {

    return this.acceptedCommercial;
  }

  /**
   * @param acceptedCommercial the acceptedCommercial to set
   */
  @Override
  public void setAcceptedCommercial(Boolean acceptedCommercial) {

    this.acceptedCommercial = acceptedCommercial;
  }

  /**
   * @return the acceptedTerms
   */
  @Override
  public Boolean getAcceptedTerms() {

    return this.acceptedTerms;
  }

  /**
   * @param acceptedTerms the acceptedTerms to set
   */
  @Override
  public void setAcceptedTerms(Boolean acceptedTerms) {

    this.acceptedTerms = acceptedTerms;
  }

  /**
   * @return the userType
   */
  @Override
  public Boolean getUserType() {

    return this.userType;
  }

  /**
   * @param userType the userType to set
   */
  @Override
  public void setUserType(Boolean userType) {

    this.userType = userType;
  }

}
