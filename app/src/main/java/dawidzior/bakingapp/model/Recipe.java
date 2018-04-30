package dawidzior.bakingapp.model;

import org.parceler.Parcel;

import java.util.List;

import lombok.Getter;

@Parcel
@Getter
public class Recipe {

    String name;

    List<Ingredient> ingredients;

    List<Step> steps;

    String image;
}
