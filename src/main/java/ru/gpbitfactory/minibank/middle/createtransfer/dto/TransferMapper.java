package ru.gpbitfactory.minibank.middle.createtransfer.dto;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.gpbitfactory.minibank.backend.dto.CreateTransferRequestV2;

@Component
@RequiredArgsConstructor
public class TransferMapper {

    private final ModelMapper modelMapper;

    public CreateTransferRequestV2 map(CreateTransferRequest createTransferRequest) {
        return modelMapper.map(createTransferRequest, CreateTransferRequestV2.class);
    }
}
