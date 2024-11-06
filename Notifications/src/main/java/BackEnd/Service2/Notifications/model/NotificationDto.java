package BackEnd.Service2.Notifications.model;


import org.antlr.v4.runtime.misc.NotNull;

public record NotificationDto(Long id,
                              String NotificationContent,
                              @NotNull
                              Long number) {

}
