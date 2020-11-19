export class ValidationConstants {
  static readonly MAX_LENGTH = 45;
  static readonly MIN_LENGTH = 3;
  static readonly FLOAT_NUMBERS = new RegExp(/^([0-9]{1,3}[.])?[0-9]{1,2}$/);
  static readonly FINANCIAL = new RegExp(/^([0-9]{1,3}[.])?[0-9]{1,2}$/);
  static readonly DIGITS = new RegExp(/^\d+$/);
  static readonly LETTERS = '[a-z-A-Z-а-я-А-Я]*';
}
