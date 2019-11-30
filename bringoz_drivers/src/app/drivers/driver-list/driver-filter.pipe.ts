import { Pipe, PipeTransform} from '@angular/core';
import { IDriver } from '../IDriver';

@Pipe({
    name:'driverFilter'
})
export class DriverFilterPipe implements PipeTransform{
transform(value: IDriver[], filterBy: string): IDriver[] {

    filterBy = filterBy ? filterBy.toLocaleLowerCase() : null;
    return filterBy ? value.filter((driver: IDriver) =>
          driver.firstName.toLocaleLowerCase().indexOf(filterBy) !== -1) : value;
  }
}