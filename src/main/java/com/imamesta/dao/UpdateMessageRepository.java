package com.imamesta.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imamesta.domain.UpdateMessage;

public interface UpdateMessageRepository extends JpaRepository<UpdateMessage, Long> {

}
