package com.cga.outlier.db;

import com.cga.outlier.db.tables.Pricedata;
import com.cga.outlier.db.tables.records.PricedataRecord;
import com.cga.outlier.domian.PriceData;
import com.cga.outlier.persistance.HikariCPDataSource;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.time.OffsetDateTime;
import java.util.List;

import static com.cga.outlier.db.tables.Pricedata.PRICEDATA;

public class PriceDataRepo {

    public List<PriceData> getPriceDataFrom(int days) {
        final DSLContext dslContext = DSL.using(HikariCPDataSource.getInstance(),
                SQLDialect.POSTGRES);
        final var data = dslContext.selectFrom(PRICEDATA)
                .where(PRICEDATA.PRICE_DATE.between(OffsetDateTime.now().minusDays(days),
                        OffsetDateTime.now()))
                .fetch(record -> new PriceData(record.getPriceDate().toLocalDate(), record.getPrice()));
        return data;
    }
}
