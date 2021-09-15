import { Pipe, PipeTransform } from '@angular/core';
import { Sweet } from '../Models/sweet';

@Pipe({
  name: 'sweetnull'
})
export class SweetnullPipe implements PipeTransform {
  transform(sweets: Sweet[],filterBy: string): Sweet[] {
    filterBy = filterBy ? filterBy.toLocaleLowerCase() : null;
    return filterBy ? sweets.filter((check: Sweet) =>
      check.image !== null && check.image.toLocaleLowerCase().indexOf(filterBy) !== -1) : sweets;}


}
