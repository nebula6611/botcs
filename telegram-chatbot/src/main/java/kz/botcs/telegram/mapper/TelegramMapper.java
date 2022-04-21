package kz.botcs.telegram.mapper;

import kz.botcs.chatbot.InMessage;
import kz.botcs.chatbot.outmessage.BottomMenuOutMessage;
import kz.botcs.chatbot.outmessage.TextOutMessage;
import kz.botcs.telegram.dto.MessageTo;
import kz.botcs.telegram.dto.Update;

public interface TelegramMapper {
    InMessage toInMessage(Update update);

    MessageTo toMessageTo(Integer userIdInt, TextOutMessage outMessage);

    MessageTo toMessageTo(Integer userIdInt, BottomMenuOutMessage outMessage);
}
