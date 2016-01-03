package com.ph.test;

import java.math.BigDecimal;

import org.jooq.DSLContext;
import org.jooq.InsertValuesStep4;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import com.ph.db.JooqConnection;
import com.ph.jooq.tables.Bigtable;
import com.ph.jooq.tables.records.BigtableRecord;

public class JooqTest {
	/**
	 * save an entry in Family table
	 * @param create
	 */
	private static void store(DSLContext create) {
		
		InsertValuesStep4<BigtableRecord, Integer, String, Integer, BigDecimal> step4 = create.insertInto(Bigtable.BIGTABLE, Bigtable.BIGTABLE.ID, Bigtable.BIGTABLE.NAME, Bigtable.BIGTABLE.AGE, Bigtable.BIGTABLE.MONEY);
		for (int i = 0; i < 10; i++) {
			step4.values(10+i, "jooq" + String.valueOf(10 + i), 10+i, BigDecimal.valueOf((10 + i) + (10 + i) * 0.01));
		}
		step4.execute();
		
		System.out.println("BigtableRecord RECORDS saved");
	}
	/**
	 * select all entries in Table Family
	 * @param create
	 */
	private static void selection(DSLContext create) {
		// SELECTING RECORDS
		Result<Record> bigtables = create.select().from(Bigtable.BIGTABLE).orderBy(Bigtable.BIGTABLE.ID).fetch();
		System.out.println("id \t name \t age \t money");
		for (Record f : bigtables) {
			BigtableRecord fr = (BigtableRecord) f;
			System.out.println(fr.getId() + " \t " + fr.getName() + " \t " + fr.getAge() + " \t " + fr.getMoney());
		}

		System.out.println("Bigtable RECORDS selected");
	}
	
	/**
	 * udate record  with id=0;
	 * @param create
	 */
	private static void update(DSLContext create) {
		// SELECTING RECORDS
		create.update(Bigtable.BIGTABLE)

		.set(Bigtable.BIGTABLE.AGE, 110)

		.where(Bigtable.BIGTABLE.ID.equal(10)).execute();

		System.out.println("Bigtable RECORD updated");

	}
	
	/**
	 * delete all records in family table
	 * @param create
	 */
	private static void deleteOne(DSLContext create) {
		// deleting RECORDS
		create.delete(Bigtable.BIGTABLE).where(Bigtable.BIGTABLE.ID.equal(10)).execute();
		System.out.println("Bigtable delete one record");
	}
	
	/**
	 * delete all records in family table
	 * @param create
	 */
	private static void deleteAll(DSLContext create) {
		// deleting RECORDS
		create.delete(Bigtable.BIGTABLE).execute();
		System.out.println("Bigtable RECORDS deleted");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			DSLContext create = DSL.using(JooqConnection.getConnection(),
					SQLDialect.POSTGRES);
			
			// INSERTING of records
			store(create);

			// selection of records
			selection(create);

			// update of records
			update(create);
			
			// selection of records
			selection(create);
			
			//delete one record
			deleteOne(create);
			
			// selection of records
			selection(create);
						
			//delete entries
			deleteAll(create);

			// selection of records
			selection(create);
			
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
