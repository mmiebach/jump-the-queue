package com.devonfw.application.jtqj.accesscodemanagement.logic.api.usecase;

import java.util.List;

import org.springframework.data.domain.Page;

import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeCto;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeEto;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeSearchCriteriaTo;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.RemainingCodes;

public interface UcFindAccessCode {

  /**
   * Returns a composite AccessCode by its id 'id'.
   *
   * @param id The id 'id' of the AccessCode.
   * @return The {@link AccessCodeCto} with id 'id'
   */
  AccessCodeCto findAccessCodeCto(long id);

  /**
   * Returns a paginated list of composite AccessCodes matching the search criteria.
   *
   * @param criteria the {@link AccessCodeSearchCriteriaTo}.
   * @return the {@link List} of matching {@link AccessCodeCto}s.
   */
  Page<AccessCodeCto> findAccessCodeCtos(AccessCodeSearchCriteriaTo criteria);

  /**
   * Returns a next AccessCode of dailyQueue.
   *
   * @param id The id 'id' of the dailyQueue.
   * @return The {@link AccessCodeEto} with status waiting and farthest in createdTime
   */
  RemainingCodes findRemainingCodes(AccessCodeEto accessCode);

}
