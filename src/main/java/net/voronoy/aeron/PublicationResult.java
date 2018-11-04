package net.voronoy.aeron;

import io.aeron.Publication;

public enum PublicationResult {
    NOT_CONNECTED("The publication is not yet connected to a subscriber"),
    BACK_PRESSURED("The offer failed due to back pressure from the subscribers preventing further transmission"),
    ADMIN_ACTION("The offer failed due to an administration action and should be retried. " +
            "The action is an operation such as log rotation which is likely to have succeeded by the next retry attempt"),
    CLOSED("The Publication has been closed and should no longer be used"),
    MAX_POSITION_EXCEEDED("The offer failed due to reaching the maximum position of the stream given term buffer " +
            "length times the total possible number of terms. If this happens then the publication should be closed and " +
            "a new one added. To make it less likely to happen then increase the term buffer length"),
    OK("Publication succeeded");

    private final String description;

    PublicationResult(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    static PublicationResult create(int value) {
        switch (value) {
            case -1:
                return NOT_CONNECTED;
            case -2:
                return BACK_PRESSURED;
            case -3:
                return ADMIN_ACTION;
            case -4:
                return CLOSED;
            case -5:
                return MAX_POSITION_EXCEEDED;
            default:
                return OK;
        }
    }
}
