package kz.botcs.telegram.mapper;

import kz.botcs.chatbot.InMessage;
import kz.botcs.chatbot.outmessage.BottomMenuOutMessage;
import kz.botcs.chatbot.outmessage.PhotoOutMessage;
import kz.botcs.chatbot.outmessage.TextOutMessage;
import kz.botcs.telegram.dto.*;
import org.mapstruct.Mapping;

public interface TelegramMapper {
    InMessage toInMessage(Update update);

    MessageTo toMessageTo(Integer userIdInt, TextOutMessage outMessage);

    EditMessage toEditMessage(Integer userId, TextOutMessage textOutMessage);

    Photo toPhoto(Integer userId, PhotoOutMessage photoOutMessage);

    ReplyKeyboardMarkup toReplyKeyboardMarkup(BottomMenuOutMessage outMessage);

}
