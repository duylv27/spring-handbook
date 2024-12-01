package com.spring.handbook.data.repository.projection;

import com.spring.handbook.data.dto.AddressDTO;

public interface UserProjection {

    Long getId();

    AddressDTO getAddress();

}
