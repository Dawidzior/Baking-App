package dawidzior.bakingapp.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import lombok.Getter;

@Getter
@Parcel
public class Ingredient {

    double quantity;

    Measure measure;

    @SerializedName("ingredient")
    String name;
}
