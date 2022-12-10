package com.ouieat.requests.recommendation;

import com.ouieat.implementation.recommendation.RecommendationImplementation;
import com.ouieat.interactor.recommendation.RecommendationInteractor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.recommendation.Recommendation;
import com.ouieat.models.user.User;
import com.ouieat.requests.handler.AuthenticatedRequest;
import com.ouieat.requests.handler.FunctionalInterfaces;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedRecommendationRequest
    extends AuthenticatedRequest<RecommendationInteractor> {

    public FunctionalInterfaces.Function3<RecommendationInteractor, User, Recommendation, Response> postRecommendation = (
        RecommendationInteractor interactor,
        User loggedInUser,
        Recommendation recommendation
    ) -> {
        if (
            recommendation == null || recommendation.getRestaurantId() == null
        ) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }
        return RecommendationImplementation.postRecommendation(
            this.interactor,
            loggedInUser,
            recommendation
        );
    };

    public FunctionalInterfaces.Function2<RecommendationInteractor, User, Response> getRecommendationsForUser = (
            interactor1,
            loggedInUser
        ) ->
        RecommendationImplementation.getRecommendationsForUser(
            this.interactor,
            loggedInUser
        );

    @Autowired
    public AuthenticatedRecommendationRequest(
        UserInteractor userInteractor,
        RecommendationInteractor interactor
    ) {
        super(userInteractor, interactor);
    }
}
