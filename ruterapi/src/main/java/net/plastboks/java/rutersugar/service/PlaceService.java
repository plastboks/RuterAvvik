package net.plastboks.java.rutersugar.service;

import net.plastboks.java.rutersugar.model.Place;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface PlaceService
{
    @GET("/Place/GetPlaceExtension/{id}")
    Call<Place> getPlaceExtension(@Path("id") int id,
                                  @Query("locationType") String locationType);
    @GET("/Place/GetPlaceExtension/{id}")
    Observable<Place> getPlaceExtensionRx(@Path("id") int id,
                                        @Query("locationType") String locationType);

    @GET("/Place/GetPlacesByIdListExtension")
    Call<List<Place>> getPlaceByIdListExtension();
    @GET("/Place/GetPlacesByIdListExtension")
    Observable<List<Place>> getPlaceByIdListExtensionRx();

    @GET("/Place/GetPlacesByAreaExtension")
    Call<List<Place>> getPlacesByAreaExtension(@Query("xmin") String xmin,
                                               @Query("xmax") String xmax,
                                               @Query("ymin") String ymin,
                                               @Query("ymax") String ymax,
                                               @Query("unique") boolean unique,
                                               @Query("locationType") String locationType);
    @GET("/Place/GetPlacesByAreaExtension")
    Observable<List<Place>> getPlacesByAreaExtensionRx(@Query("xmin") String xmin,
                                         @Query("xmax") String xmax,
                                         @Query("ymin") String ymin,
                                         @Query("ymax") String ymax,
                                         @Query("unique") boolean unique,
                                         @Query("locationType") String locationType);

    @GET("/Place/GetPlacesByAreaExtension")
    Call<List<Place>> getPlacesByAreaExtension(@Query("xmin") String xmin,
                                         @Query("xmax") String xmax,
                                         @Query("ymin") String ymin,
                                         @Query("ymax") String ymax,
                                         @Query("unique") boolean unique,
                                         @Query("locationType") String locationType,
                                         @Query("counties") List<String> counties);
    @GET("/Place/GetPlacesByAreaExtension")
    Observable<List<Place>> getPlacesByAreaExtensionRx(@Query("xmin") String xmin,
                                               @Query("xmax") String xmax,
                                               @Query("ymin") String ymin,
                                               @Query("ymax") String ymax,
                                               @Query("unique") boolean unique,
                                               @Query("locationType") String locationType,
                                               @Query("counties") List<String> counties);

    @GET("/Place/GetClosestPlacesExtension")
    Call<List<Place>> getClosestPlacesExtension(@Query("proposals") int proposals,
                                          @Query("maxdistance") int maxdistance,
                                          @Query("unique") boolean unique,
                                          @Query("locationType") String locationType);
    @GET("/Place/GetClosestPlacesExtension")
    Observable<List<Place>> getClosestPlacesExtensionRx(@Query("proposals") int proposals,
                                                @Query("maxdistance") int maxdistance,
                                                @Query("unique") boolean unique,
                                                @Query("locationType") String locationType);

    @GET("/Place/GetClosestPlacesExtension")
    Call<List<Place>> getClosestPlacesExtension(@Query("proposals") int proposals,
                                          @Query("maxdistance") int maxdistance,
                                          @Query("unique") boolean unique,
                                          @Query("locationType") String locationType,
                                          @Query("counties") List<String> counties);
    @GET("/Place/GetClosestPlacesExtension")
    Observable<List<Place>> getClosestPlacesExtensionRx(@Query("proposals") int proposals,
                                                @Query("maxdistance") int maxdistance,
                                                @Query("unique") boolean unique,
                                                @Query("locationType") String locationType,
                                                @Query("counties") List<String> counties);

    @GET("/Place/GetPlaces/{id}")
    Call<List<Place>> getPlaces(@Path("id") String id);
    @GET("/Place/GetPlaces/{id}")
    Observable<List<Place>> getPlacesRx(@Path("id") String id);

    @GET("/Place/GetPlaces/{id}")
    Call<List<Place>> getPlaces(@Path("id") String id,
                          @Query("counties") List<String> counties);
    @GET("/Place/GetPlaces/{id}")
    Observable<List<Place>> getPlacesRx(@Path("id") String id,
                                @Query("counties") List<String> counties);

    @GET("/Place/GetPlacesExtension/{id}")
    Call<List<Place>> getPlacesExtension(@Path("id") String id,
                                   @Query("location") String location,
                                   @Query("unique") boolean unique,
                                   @Query("locationType") String locationType,
                                   @Query("proposals") int proposals);
    @GET("/Place/GetPlacesExtension/{id}")
    Observable<List<Place>> getPlacesExtensionRx(@Path("id") String id,
                                         @Query("location") String location,
                                         @Query("unique") boolean unique,
                                         @Query("locationType") String locationType,
                                         @Query("proposals") int proposals);

    @GET("/Place/GetPlacesExtension/{id}")
    Call<List<Place>> getPlacesExtension(@Path("id") String id,
                                   @Query("location") String location,
                                   @Query("unique") boolean unique,
                                   @Query("locationType") String locationType,
                                   @Query("proposals") int proposals,
                                   @Query("counties") List<String> counties);
    @GET("/Place/GetPlacesExtension/{id}")
    Observable<List<Place>> getPlacesExtensionRx(@Path("id") String id,
                                         @Query("location") String location,
                                         @Query("unique") boolean unique,
                                         @Query("locationType") String locationType,
                                         @Query("proposals") int proposals,
                                         @Query("counties") List<String> counties);

    // Method name and resource name not matching. This call returns a list of
    // places, not a list of stops like the resource indicates.
    @GET("/Place/GetStopsByLineID/{id}")
    Call<List<Place>> getPlacesByLineId(@Path("id") int id);
    @GET("/Place/GetStopsByLineID/{id}")
    Observable<List<Place>> getPlacesByLineIdRx(@Path("id") int id);

    @GET("/Place/GetClosestStops")
    Call<List<Place>> getClosestStops(@Query("proposals") int proposals,
                                @Query("maxdistance") int maxdistance);
    @GET("/Place/GetClosestStops")
    Observable<List<Place>> getClosestStopsRx(@Query("proposals") int proposals,
                                      @Query("maxdistance") int maxdistance);

    @GET("/Place/GetStopsByArea")
    Call<List<Place>> getStopsByArea(@Query("xmin") int xmin,
                               @Query("xmax") String xmax,
                               @Query("ymin") String ymin,
                               @Query("ymax") String ymax,
                               @Query("includeStopPoints") boolean includeStopPoints);
    @GET("/Place/GetStopsByArea")
    Observable<List<Place>> getStopsByAreaRx(@Query("xmin") int xmin,
                                     @Query("xmax") String xmax,
                                     @Query("ymin") String ymin,
                                     @Query("ymax") String ymax,
                                     @Query("includeStopPoints") boolean includeStopPoints);
}
