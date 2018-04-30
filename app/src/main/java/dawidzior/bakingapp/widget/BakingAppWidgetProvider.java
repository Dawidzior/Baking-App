package dawidzior.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Arrays;
import java.util.List;

import dawidzior.bakingapp.R;

public class BakingAppWidgetProvider extends AppWidgetProvider {

    private static final String ACTION_NEXT_RECIPE = "ACTION_NEXT_RECIPE";

    private static String nutellaPieIngredients =
            "2.0 CUP Graham Cracker crumbs; 6.0 TBLSP unsalted butter, melted; 0.5 CUP granulated sugar; 1.5 TSP salt; 5.0 TBLSP vanilla; 1.0 K Nutella or other chocolate-hazelnut spread; 500.0 G Mascapone Cheese(room temperature); 1.0 CUP heavy cream(cold); 4.0 OZ cream cheese(softened)";

    private static String browniesIngredients =
            "350.0 G Bittersweet chocolate (60-70% cacao); 226.0 G unsalted butter; 300.0 G granulated sugar; 100.0 G light brown sugar; 5.0 UNIT large eggs; 1.0 TBLSP vanilla extract; 140.0 G all purpose flour; 40.0 G cocoa powder; 1.5 TSP salt; 350.0 G semisweet chocolate chips";

    private static String yellowCakeIngredients =
            "400.0 G sifted cake flour; 700.0 G granulated sugar; 4.0 TSP baking powder; 1.5 TSP salt; 2.0 TBLSP vanilla extract, divided; 8.0 UNIT egg yolks; 323.0 G whole milk; 961.0 G unsalted butter, softened and cut into 1 in. cubes; 6.0 UNIT egg whites; 283.0 G melted and cooled bittersweet or semisweet chocolate";

    private static String cheescakeIngredients =
            "2.0 CUP Graham Cracker crumbs; 6.0 TBLSP unsalted butter, melted; 250.0 G granulated sugar; 1.0 TSP salt; 4.0 TSP vanilla,divided; 680.0 G cream cheese, softened; 3.0 UNIT large whole eggs; 2.0 UNIT large egg yolks; 250.0 G heavy cream";

    private static List<String> ingredientsList = Arrays.asList(nutellaPieIngredients, browniesIngredients,
            yellowCakeIngredients, cheescakeIngredients);

    private static int currentIngredients = 0;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget_provider);
        views.setTextViewText(R.id.widget_text, ingredientsList.get(0));
        Intent intent = new Intent(context, BakingAppWidgetProvider.class);
        intent.setAction(ACTION_NEXT_RECIPE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.widget_container, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (ACTION_NEXT_RECIPE.equals(intent.getAction())) {
            currentIngredients++;
            if (currentIngredients == ingredientsList.size()) currentIngredients = 0;
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget_provider);
            views.setTextViewText(R.id.widget_text, ingredientsList.get(currentIngredients));
            ComponentName appWidget = new ComponentName(context, BakingAppWidgetProvider.class);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            appWidgetManager.updateAppWidget(appWidget, views);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}

