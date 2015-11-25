package com.ibm.optim.oaas.sample.trucking.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;

public class ResultDeserializerContext extends DefaultDeserializationContext {
	
	private static final long serialVersionUID = 833839947258259165L;

	Map<String, Location> locations = new HashMap<String, Location>();
	Map<String, TruckType> truckTypes = new HashMap<String, TruckType>();

	public ResultDeserializerContext(Problem pb) {
		super(BeanDeserializerFactory.instance, null);
		for (Location l : pb.getHubs()) {
			locations.put(l.getName(), l);
		}
		for (Location l : pb.getSpokes()) {
			locations.put(l.getName(), l);
		}
		for (TruckType t : pb.getTruckTypes()) {
			truckTypes.put(t.getTruckType(), t);
		}
	}

	public ResultDeserializerContext(DeserializerFactory df) {
		super(df, null);
	}

	protected ResultDeserializerContext(ResultDeserializerContext src,
			DeserializationConfig config, JsonParser jp, InjectableValues values) {
		super(src, config, jp, values);
		locations = src.locations;
		truckTypes = src.truckTypes;
	}

	protected ResultDeserializerContext(ResultDeserializerContext src,
			DeserializerFactory factory) {
		super(src, factory);
		locations = src.locations;
		truckTypes = src.truckTypes;
	}

	@Override
	public DefaultDeserializationContext createInstance(
			DeserializationConfig config, JsonParser jp, InjectableValues values) {
		return new ResultDeserializerContext(this, config, jp, values);
	}

	@Override
	public DefaultDeserializationContext with(DeserializerFactory factory) {
		return new ResultDeserializerContext(this, factory);
	}

	// This code works with Jackson 2.2.2
	@Override
	public ReadableObjectId findObjectId(Object id, ObjectIdGenerator<?> generator) {
		ReadableObjectId oid = super.findObjectId(id, generator);
		if (oid.item == null) {
			Object object = findObject(generator.getScope(), id);
			if (object != null)
				try {
					oid.bindItem(object);
				} catch (IOException ex) {
					throw new IllegalStateException("Unable to bind " + object + " to " + id, ex);
				}
		}
		return oid;
	}

	/**
	 * Code to use for Jackson > 2.4.0
	@Override
	public ReadableObjectId findObjectId(Object id, ObjectIdGenerator<?> generator, ObjectIdResolver resolverType) {
		ReadableObjectId oid = super.findObjectId(id, generator,resolverType);
		if (oid == null || oid.resolve()==null) {
			Object object = findObject(generator.getScope(), id);
			if (object != null)
				try {
					oid.bindItem(object);
				} catch (IOException ex) {
					throw new IllegalStateException("Unable to bind " + object + " to " + id, ex);
				}
		}
		return oid;
	}
	*/
	
	protected Object findObject(Class<?> scope, Object id) {
		if (scope.equals(Location.class)) {
			return locations.get(id);
		} else if (scope.equals(TruckType.class)) {
			return truckTypes.get(id);
		}
		return null;
	}
}
