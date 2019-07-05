
package com.codepath.apps.restclienttemplate.models;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated("org.parceler.ParcelAnnotationProcessor")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class Entities$$Parcelable
    implements Parcelable, ParcelWrapper<com.codepath.apps.restclienttemplate.models.Entities>
{

    private com.codepath.apps.restclienttemplate.models.Entities entities$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Creator<Entities$$Parcelable>CREATOR = new Creator<Entities$$Parcelable>() {


        @Override
        public Entities$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new Entities$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public Entities$$Parcelable[] newArray(int size) {
            return new Entities$$Parcelable[size] ;
        }

    }
    ;

    public Entities$$Parcelable(com.codepath.apps.restclienttemplate.models.Entities entities$$2) {
        entities$$0 = entities$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(entities$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.codepath.apps.restclienttemplate.models.Entities entities$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(entities$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(entities$$1));
            parcel$$1 .writeInt((entities$$1 .has_entity? 1 : 0));
            parcel$$1 .writeString(entities$$1 .media_url);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.codepath.apps.restclienttemplate.models.Entities getParcel() {
        return entities$$0;
    }

    public static com.codepath.apps.restclienttemplate.models.Entities read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.codepath.apps.restclienttemplate.models.Entities entities$$4;
            int reservation$$0 = identityMap$$1 .reserve();
            entities$$4 = new com.codepath.apps.restclienttemplate.models.Entities();
            identityMap$$1 .put(reservation$$0, entities$$4);
            entities$$4 .has_entity = (parcel$$3 .readInt() == 1);
            entities$$4 .media_url = parcel$$3 .readString();
            com.codepath.apps.restclienttemplate.models.Entities entities$$3 = entities$$4;
            identityMap$$1 .put(identity$$1, entities$$3);
            return entities$$3;
        }
    }

}
