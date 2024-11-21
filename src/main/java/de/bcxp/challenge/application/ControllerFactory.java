package de.bcxp.challenge.application;

/**
 * Interface for cronstructing different Controller so that the application does not have to do it
 *
 * @param <T> the type of the Controller
 */
public interface ControllerFactory<T extends Controller> {

    T createController();
}
