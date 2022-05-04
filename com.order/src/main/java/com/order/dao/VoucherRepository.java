package com.order.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.order.entity.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
	@Query(value = "SELECT * FROM voucher v WHERE v.end_date > :now and v.published_date < :now", nativeQuery = true)
	List<Voucher> getPublishList(Date now);
}
