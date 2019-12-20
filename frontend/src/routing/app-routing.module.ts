import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AirportComponent} from "../app/airport/airport.component";
import {FareComponent} from "../app/fare/fare.component";


const appRoutes: Routes = [
    {path: '', component: FareComponent},
    {path: 'travel', component: FareComponent},
    {path: 'airports', component: AirportComponent},

];

@NgModule({
    imports: [RouterModule.forRoot(appRoutes, {enableTracing: false})],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
