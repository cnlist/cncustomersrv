package us.cnlist.services.customerserver.database.entity.help;

import us.cnlist.objects.geo.GeoObject;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GeoObjectToLong implements AttributeConverter<GeoObject, Long> {
    @Override
    public Long convertToDatabaseColumn(GeoObject geoObject) {
        return geoObject.getId();
    }

    @Override
    public GeoObject convertToEntityAttribute(Long aLong) {
        GeoObject object = new GeoObject();
        object.setId(aLong);
        return object;
    }
}
