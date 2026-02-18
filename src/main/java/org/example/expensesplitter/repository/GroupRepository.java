package org.example.expensesplitter.repository;

import java.util.List;

import org.example.expensesplitter.dtos.ExpenseResponse;
import org.example.expensesplitter.dtos.GroupOverviewResponse;
import org.example.expensesplitter.dtos.MemberResponse;
import org.example.expensesplitter.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    @Query("""
            select new org.example.expensesplitter.dtos.MemberResponse(m.id, m.name)
            from Member m
            where m.group.id = :groupId
            """)
    List<MemberResponse> findAllMembersById(@Param("groupId") int groupId);

    @Query("""
            select new org.example.expensesplitter.dtos.ExpenseResponse(e.id, e.reason, e.payer.name, e.payedAt)
            from Expense e
            where e.group.id = :groupId
            """)
    List<ExpenseResponse> findAllExpenesById(@Param("groupId") int groupId);

    @Query("""
            select sum(e.value)
            from Expense e
            where e.group.id = :groupId
            """)
    Double getTotalSpent(@Param("groupId") int groupId);

    @Query("""
            select new org.example.expensesplitter.dtos.GroupOverviewResponse(g.id, g.name, g.active, g.createdAt, SIZE(g.members))
			from Group g
			where g.owner.id = :ownerId
            """)
	List<GroupOverviewResponse> findAllGroupsByOwnerId(@Param("ownerId") int ownerId);
}
