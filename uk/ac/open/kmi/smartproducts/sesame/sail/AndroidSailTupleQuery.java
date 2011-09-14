package uk.ac.open.kmi.smartproducts.sesame.sail;

import org.openrdf.query.parser.ParsedTupleQuery;
import org.openrdf.repository.sail.SailRepositoryConnection;
import org.openrdf.repository.sail.SailTupleQuery;

public class AndroidSailTupleQuery extends SailTupleQuery {

	public AndroidSailTupleQuery(ParsedTupleQuery tupleQuery,
			SailRepositoryConnection sailConnection) {
		super(tupleQuery, sailConnection);
		// TODO Auto-generated constructor stub
	}

}
