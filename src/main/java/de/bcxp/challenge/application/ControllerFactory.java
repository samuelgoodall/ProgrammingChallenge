package de.bcxp.challenge.application;

public interface ControllerFactory<F, T extends Controller<F>> {

    T createController();
}
