package com.timife.kotlinbackend.repositories

import com.timife.kotlinbackend.domain.entities.IssueEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IssueRepository : CrudRepository<IssueEntity, String>{

}